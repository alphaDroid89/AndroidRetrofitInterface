package com.retrofit.sample.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retrofit.sample.R;
import com.retrofit.sample.main.SampleRetrofitApp;
import com.retrofit.sample.model.AppResponseBean;
import com.retrofit.sample.rest.IResponseListener;
import com.retrofit.sample.rest.RetroBase;
import com.retrofit.sample.utils.AppLog;
import com.retrofit.sample.utils.ProgressDialogUtils;
import com.retrofit.sample.utils.typedef.RequestTypeDef;
import com.retrofit.sample.view.adapter.ApplicationAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements IResponseListener,ApplicationAdapter.OnItemClickListener {

    Call<ResponseBody> result;
    MainActivity getActivityContext;
    private RecyclerView rvappslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityContext = this;
        this.rvappslist = findViewById(R.id.rv_apps_list);
        getNewApplicationsCall();
    }

    private void getNewApplicationsCall() {
        ProgressDialogUtils.getInstance().showProgressDialog(getActivityContext, "Loading...!");
        RetroBase.getInstance().loadApiCall(SampleRetrofitApp.getApplicationInstance().getNetworkClient().getAPI().getNewApplications(), this, RequestTypeDef.API_REQUEST_GET_NEW_APPS);
    }

    @Override
    public void onSuccess(String response, int requestId) {
        ProgressDialogUtils.getInstance().dismissProgressDialog();
        switch (requestId) {
            case RequestTypeDef.API_REQUEST_GET_NEW_APPS:
                try {
                    AppLog.d("onGetSuccess - getResponse", response);
                    final ObjectMapper mapper = new ObjectMapper();
                    AppResponseBean appResponseBean = mapper.readValue(response, AppResponseBean.class);
                    rvappslist.setHasFixedSize(true);
                    rvappslist.setAdapter(new ApplicationAdapter(this, appResponseBean.getFeed().getEntry(),this));
                    rvappslist.setLayoutManager(new LinearLayoutManager(this));
                    rvappslist.setItemAnimator(new DefaultItemAnimator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void serverError(String stringFromByte, int paramInt, int errorCode) {
        ProgressDialogUtils.getInstance().dismissProgressDialog();
        AppLog.e("serverError - error", errorCode + " - " + stringFromByte);

    }

    @Override
    public void onFailure(Throwable t, int requestId) {
        ProgressDialogUtils.getInstance().dismissProgressDialog();
        AppLog.e("onFailure - error", requestId + " - " + t.getMessage());
    }

    @Override
    public void onItemClick(int position) {

    }
}
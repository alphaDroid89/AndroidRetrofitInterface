package com.sample.retrointerface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.sample.retrointerface.rest.RetrofitClient;
import com.sample.retrointerface.rest.RetrofitResultInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements RetrofitResultInterface {
    protected TextView mTxtResponseText;
    MainActivity getActivityContext;
    public static final int RESULT_GET_RESPONSE = 001;
    Call<ResponseBody> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityContext = this;
        super.setContentView(R.layout.activity_main);
        initView();

        result = RetrofitClient.getApiService().getNewApplications();
        RetrofitClient.callGetResponseString(getActivityContext, this, result, RESULT_GET_RESPONSE);

    }

    @Override
    public void onGetFailed(String message, int msgID) {
        Log.e("onGetFailed", message);

    }

    @Override
    public void onGetSuccess(String getResponse, int msgID) {
        switch (msgID) {
            case RESULT_GET_RESPONSE:
                Log.d("onGetSuccess - getResponse", getResponse);
                mTxtResponseText.setText(getResponse);
                break;
        }
    }

    private void initView() {
        mTxtResponseText = (TextView) findViewById(R.id.txt_response_text);
    }
}

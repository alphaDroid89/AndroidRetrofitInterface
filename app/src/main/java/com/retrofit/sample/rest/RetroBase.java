package com.retrofit.sample.rest;

import android.annotation.SuppressLint;

import com.retrofit.sample.main.SampleRetrofitApp;
import com.retrofit.sample.utils.AppLog;
import com.retrofit.sample.utils.AppToast;
import com.retrofit.sample.utils.NetworkUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class RetroBase {
    private static String TAG = RetroBase.class.getSimpleName();
    private static RetroBase rxRetro;

    public static RetroBase getInstance() {
        if (rxRetro == null) {
            rxRetro = new RetroBase();
        }
        return rxRetro;
    }

    private String getStringFromByte(InputStream paramInputStream) {
        StringBuilder localStringBuilder = new StringBuilder();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
        try {
            while (true) {
                String str = localBufferedReader.readLine();
                if (str == null)
                    break;
                localStringBuilder.append(str);
            }
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return localStringBuilder.toString();
    }

    /**
     * @param call              - retrofit request call
     * @param iResponseListener - Response will be send to the classes where iResponseListener is implemented
     * @param requestId         - type Id of each request
     *                          - multiple api call from one activity can be handled with different requestId
     */

    @SuppressLint("CheckResult")
    public void loadApiCall(Observable<Response<ResponseBody>> call, final IResponseListener iResponseListener, final int requestId) {

        if (!NetworkUtils.isConnected(SampleRetrofitApp.getApplicationInstance())) {
            AppToast.getInstance().showToast(SampleRetrofitApp.getApplicationInstance(), "Please check your Network Connectivity.");
            return;
        }
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                    @Override
                    public void onNext(Response<ResponseBody> paramResponse) {

                        AppLog.d(TAG, "RESPONSE CODE - " + paramResponse.raw().code());
                        AppLog.d(TAG, "RAW RESPONSE - " + paramResponse.raw());

                        Object localObject = paramResponse.body();
                        String str = null;
                        if (localObject != null) {
                            str = getStringFromByte(getByteStream(Objects.requireNonNull(paramResponse.body())));
                            AppLog.d(TAG, "=" + str);
                        }

                        switch (paramResponse.code()) {
                            case NetworkConstants.HttpRequests.STATUS_OK:
                                iResponseListener.onSuccess(str, requestId);
                                break;
                            case NetworkConstants.HttpRequests.STATUS_CREATED:
                                iResponseListener.onSuccess(str, requestId);
                                break;
                            case NetworkConstants.HttpRequests.STATUS_BAD_REQUEST:
                                iResponseListener.serverError(getStringFromByte(getByteStream(Objects.requireNonNull(paramResponse.errorBody()))), requestId, NetworkConstants.HttpRequests.STATUS_OK);
                                break;
                            case NetworkConstants.HttpRequests.STATUS_UNAUTHORIZED:
                                iResponseListener.serverError(getStringFromByte(getByteStream(Objects.requireNonNull(paramResponse.errorBody()))), requestId, NetworkConstants.HttpRequests.STATUS_UNAUTHORIZED);
                                break;
                            case NetworkConstants.HttpRequests.STATUS_FORBIDDEN:
                                iResponseListener.serverError(paramResponse.raw().message(), requestId, NetworkConstants.HttpRequests.STATUS_FORBIDDEN);
                                break;
                            case NetworkConstants.HttpRequests.STATUS_NOT_FOUND:
                                iResponseListener.serverError(paramResponse.raw().message(), requestId, NetworkConstants.HttpRequests.STATUS_NOT_FOUND);
                                break;
                            case NetworkConstants.HttpRequests.STATUS_SERVER_ERROR:
                                iResponseListener.serverError(getStringFromByte(getByteStream(Objects.requireNonNull(paramResponse.errorBody()))), requestId, NetworkConstants.HttpRequests.STATUS_SERVER_ERROR);
                                break;
                            case NetworkConstants.HttpRequests.STATUS_NO_CONTENT:
                                iResponseListener.serverError(getStringFromByte(getByteStream(Objects.requireNonNull(paramResponse.errorBody()))), requestId, NetworkConstants.HttpRequests.STATUS_NO_CONTENT);
                                break;
                            default:
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (iResponseListener != null) {
                            iResponseListener.onFailure(e, 0);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    private InputStream getByteStream(ResponseBody paramResponse) {
        return paramResponse.byteStream();
    }
}
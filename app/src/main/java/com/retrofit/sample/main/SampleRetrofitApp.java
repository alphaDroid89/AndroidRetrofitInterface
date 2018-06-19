package com.retrofit.sample.main;

import android.app.Application;

import com.retrofit.sample.rest.NetworkClient;
import com.retrofit.sample.utils.ProgressDialogUtils;

public class SampleRetrofitApp extends Application {

    static SampleRetrofitApp getAppInstance;
    private NetworkClient networkService;

    public static SampleRetrofitApp getApplicationInstance() {
        return getAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getAppInstance = this;
        ProgressDialogUtils.initialize(getAppInstance);
        networkService = new NetworkClient();

    }

    public NetworkClient getNetworkClient() {
        return networkService;
    }
}

package com.sample.retrointerface.rest;

import android.content.Context;
import android.util.Log;

import com.sample.retrointerface.BuildConfig;
import com.sample.retrointerface.Constants;
import com.sample.retrointerface.interfaces.RetrofitInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(getOkhttpClient()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static OkHttpClient getOkhttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (!BuildConfig.RETROFIT_LOG_INTERCEPTOR) {
            return new OkHttpClient.Builder().build();
        } else {
            return new OkHttpClient.Builder().addInterceptor(interceptor).build();
        }
    }

    public static void callGetResponseString(final Context mContext, final RetrofitResultInterface resultInterface, Call<ResponseBody> result, final int msgId) {

        result.enqueue(new Callback<ResponseBody>() {
                           @Override
                           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                               if (response != null && !response.isSuccessful() && response.errorBody() != null) {
                                   //DO ERROR HANDLING HERE
//                                   resultInterface.onGetFailed(response, msgId);
                                   Log.e("callGetResponseString - onResponse - error", response.errorBody() + "");
                                   return;
                               } else {

                                   BufferedReader reader = null;
                                   StringBuilder sb = new StringBuilder();
                                   try {
                                       reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                                       String line;
                                       try {
                                           while ((line = reader.readLine()) != null) {
                                               sb.append(line);
                                           }
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   } catch (Exception e) {
                                       e.printStackTrace();
                                   }
                                   String result = sb.toString();
                                   resultInterface.onGetSuccess(result, msgId);
                               }
                           }

                           @Override
                           public void onFailure(Call<ResponseBody> call, Throwable t) {
                               resultInterface.onGetFailed(t.getMessage(), msgId);
                               Log.e("getErrorDescription", t.getMessage() + "");
                           }
                       }
        );

    }

    public static RetrofitInterface getApiService() {
        return RetrofitClient.getClient().create(RetrofitInterface.class);
    }

}
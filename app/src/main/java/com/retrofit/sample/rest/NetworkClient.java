package com.retrofit.sample.rest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.retrofit.sample.BuildConfig;
import com.retrofit.sample.utils.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class NetworkClient {
    private static final int DEFAULT_TIMEOUT = 60; //seconds
    private NetworkApi retrofitAPIInterface;
    private OkHttpClient okHttpClient;

    public NetworkClient() {
        this(Constants.BASE_URL);
    }

    public NetworkClient(String baseUrl) {
        okHttpClient = getOkhttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        retrofitAPIInterface = retrofit.create(NetworkApi.class);
    }

    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     *
     * @return
     */
    public static OkHttpClient getOkhttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (!BuildConfig.RETROFIT_LOG_INTERCEPTOR) {
            return new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        } else {
            return new OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
    }

    /**
     * Method to return the API interface.
     *
     * @return
     */
    public NetworkApi getAPI() {
        return retrofitAPIInterface;
    }

    /**
     * Method to clear the entire cache of observables
     */

    /**
     * all the Service calls to use for the retrofit requests.`
     */
    public interface NetworkApi {
        //Update Api client here
        @Headers("Content-Type: application/json")
        @POST("us/rss/newapplications/limit=50/json")
        Observable<Response<ResponseBody>> getNewApplications();

        //    if need to post/update params use like below
//    Observable<Response<ResponseBody>>  getNewApplications(@Body JsonObject object);

    }
}

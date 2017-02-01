package com.sample.retrointerface.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface RetrofitInterface {

    // Change this end point url as per yours
    @Headers("Content-Type: application/json")
    @POST("us/rss/newapplications/limit=50/json")
    Call<ResponseBody> getNewApplications();

//    if need to post/update params use like below
//    Call<ResponseBody> getNewApplications(@Body JsonObject object);

}

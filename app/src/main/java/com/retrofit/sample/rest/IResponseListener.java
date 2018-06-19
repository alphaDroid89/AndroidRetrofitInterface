package com.retrofit.sample.rest;

public interface IResponseListener extends IBaseResponseListener {
    //success response
    void onSuccess(String response, int requestId);

}
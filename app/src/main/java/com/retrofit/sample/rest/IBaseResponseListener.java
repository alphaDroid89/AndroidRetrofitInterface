package com.retrofit.sample.rest;



public interface IBaseResponseListener  {

    //apart from api call error, server communication errors
    void serverError(String stringFromByte, int paramInt, int errorCode);

    //api call failure response
    void onFailure(Throwable t, int requestId);
}
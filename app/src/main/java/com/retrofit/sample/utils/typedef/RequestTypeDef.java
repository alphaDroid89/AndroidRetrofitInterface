package com.retrofit.sample.utils.typedef;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        RequestTypeDef.API_REQUEST_GET_NEW_APPS
})
public @interface RequestTypeDef {
    int API_REQUEST_GET_NEW_APPS = 01;
}

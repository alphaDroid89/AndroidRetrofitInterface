package com.retrofit.sample.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

public class ProgressDialogUtils {
    /**
     * Add this in application class
     * ProgressDialogUtils.initialize(mAppInstance);
     */

    public static Context mContext;
    public static ProgressDialogUtils mInstance;

    private ProgressDialog progressDialog;

    public static synchronized ProgressDialogUtils getInstance() {
        if (mInstance == null) {
            return mInstance = new ProgressDialogUtils();
        }
        return mInstance;
    }

    public static void initialize(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        mContext = context;
    }

    public void showProgressDialog() {
        int style;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            style = android.R.style.Theme_Material_Light_Dialog;
        } else {
            //noinspection deprecation
            style = ProgressDialog.THEME_HOLO_LIGHT;
        }

        if (progressDialog == null && mContext != null) {
            progressDialog = new ProgressDialog(mContext, style);
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void showProgressDialog(Context mContext, String message) {
        int style;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            style = android.R.style.Theme_Material_Light_Dialog;
        } else {
            //noinspection deprecation
            style = ProgressDialog.THEME_HOLO_LIGHT;
        }

        if (progressDialog == null && mContext != null) {
            progressDialog = new ProgressDialog(mContext, style);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void dismissProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }

    }
}

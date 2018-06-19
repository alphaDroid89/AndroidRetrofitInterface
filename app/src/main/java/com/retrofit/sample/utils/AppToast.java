package com.retrofit.sample.utils;

import android.content.Context;
import android.widget.Toast;

public class AppToast {

    private static AppToast mGetInstance = null;
    public boolean isDialogShowing = false;
    private Toast mToast;

    public AppToast() {
    }

    public synchronized static AppToast getInstance() {
        if (mGetInstance == null) {
            mGetInstance = new AppToast();
        }
        return mGetInstance;
    }

    public void showToast(Context ctx, int resID) {
        showToast(ctx, Toast.LENGTH_SHORT, resID);
    }

    public void showToast(Context ctx, String text) {
        showToast(ctx, Toast.LENGTH_SHORT, text);

    }

    public void showLongToast(Context ctx, int resID) {
        showToast(ctx, Toast.LENGTH_LONG, resID);
    }

    public void showLongToast(Context ctx, String text) {
        showToast(ctx, Toast.LENGTH_LONG, text);
    }

    public void showToast(Context ctx, int duration, int resID) {
        try {
            showToast(ctx, duration, ctx.getString(resID));
        } catch (Exception e) {
            showToast(ctx, duration, resID + "");
        }
    }

    public void showToast(final Context ctx, final int duration,
                          final String text) {
        mToast = Toast.makeText(ctx, text, duration);
        mToast.show();
    }

    public void showToastDialog(Context getActivityContext, String msg) {
        if (!isDialogShowing) {
            isDialogShowing = true;
        }

    }
}
package com.retrofit.sample.utils;

import android.util.Log;

import com.retrofit.sample.BuildConfig;

public class AppLog {
    /**
     * Log state will print based on BuildConfig.LOG (This value declared in build.gradle - buildConfigField "boolean", "LOG", "true")
     * true - it prints (App running in Developer Mode (buildConfigField "boolean", "LOG", "true")
     * false - it won't print log (App Running in Release mode (buildConfigField "boolean", "LOG", "false")
     */

    public static void i(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.i(tag, getStackTraceMsg() + ": " + inputString);
    }

    public static void e(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.e(tag, getStackTraceMsg() + ": " + inputString);

    }

    public static void d(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.d(tag, getStackTraceMsg() + ": " + inputString);
    }

    public static void v(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.v(tag, getStackTraceMsg() + ": " + inputString);
    }

    public static void w(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.w(tag, getStackTraceMsg() + ": " + inputString);
    }

    /**
     * Location code location can be used only inside class
     */
    private static String getStackTraceMsg() {
        String fileInfo = "";
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements != null && stackTraceElements.length > 4) {
            StackTraceElement stackTrace = stackTraceElements[4];
            fileInfo = stackTrace.getFileName() + "(" + stackTrace.getLineNumber() + ") " + stackTrace.getMethodName();
        }
        return fileInfo;
    }

}

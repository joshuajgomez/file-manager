package com.joshgm3z.filemanager.util;

import android.util.Log;

public class Logger {

    private static final String TAG = "JGM3Z ";

    public static void a(String message) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[3];
        String className = element.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1, className.length());
        String methodName = element.getMethodName();
        Log.println(Log.ASSERT, TAG + className, methodName + " : " + message);
    }

    public static void e(String message) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[3];
        String className = element.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1, className.length());
        String methodName = element.getMethodName();
        Log.println(Log.ERROR, TAG + className, methodName + " : " + message);
    }

}

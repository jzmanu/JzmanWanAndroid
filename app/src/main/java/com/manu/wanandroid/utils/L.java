package com.manu.wanandroid.utils;

import android.util.Log;

import com.manu.wanandroid.BuildConfig;

/**
 * @Desc: L
 * @Author: jzman
 * @Date: 2019/5/9 0009 9:56
 */
public class L {

    private static boolean isDebug = BuildConfig.DEBUG;

    public static void i(String tag, String message) {
        if (isDebug)
            Log.i(tag, message);
    }
}

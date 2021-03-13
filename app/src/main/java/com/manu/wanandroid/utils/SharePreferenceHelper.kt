@file:Suppress("UNCHECKED_CAST")

package com.manu.wanandroid.utils

import android.content.Context
import com.manu.wanandroid.app.App

/**
 * 备注：获取值得时候，默认值得类型决定了要使用哪种方式获取
 * @Desc: SharePreference辅助类
 * @Author: jzman
 * @Date: 2020/8/19 16:31.
 */

private const val SHARED_PREFERENCES_NAME = "FlutterSharedPreferences"
private val sp = App.getApp().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
private val edit = sp.edit()

fun <T> getSpValue(
        key: String,
        defaultVal: T
): T {
    return when (defaultVal) {
        is Boolean -> sp.getBoolean(key, defaultVal) as T
        is String -> sp.getString(key, defaultVal) as T
        is Int -> sp.getInt(key, defaultVal) as T
        is Long -> sp.getLong(key, defaultVal) as T
        is Float -> sp.getFloat(key, defaultVal) as T
        is Set<*> -> sp.getStringSet(key, defaultVal as Set<String>) as T
        else -> throw IllegalArgumentException("Unrecognized default value $defaultVal")
    }
}

fun <T> putSpValue(
        key: String,
        value: T
) {
    when (value) {
        is Boolean -> edit.putBoolean(key, value)
        is String -> edit.putString(key, value)
        is Int -> edit.putInt(key, value)
        is Long -> edit.putLong(key, value)
        is Float -> edit.putFloat(key, value)
        is Set<*> -> edit.putStringSet(key, value as Set<String>)
        else -> throw UnsupportedOperationException("Unrecognized value $value")
    }
    edit.apply()
}

fun removeSpValue(key: String) {
    edit.remove(key)
    edit.apply()
}

fun clearSpValue() {
    edit.clear()
    edit.apply()
}
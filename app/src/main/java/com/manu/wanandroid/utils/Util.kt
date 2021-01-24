package com.manu.wanandroid.utils

import android.content.res.Resources
import android.util.Base64
import android.util.TypedValue


/**
 * @Desc: 工具类
 * @Author: jzman
 */
object Util {

    /**
     * 对一个字符串进行Base64编码
     *
     * @param str 原始字符串
     * @return 进行Base64编码后的字符串
     */
    @ExperimentalStdlibApi
    fun encodeBase64(str: String): String? {
        return Base64.encodeToString(str.encodeToByteArray(), Base64.NO_PADDING)
    }

    /**
     * dipToPx
     */
    fun dpToPx(resources: Resources, dp: Float): Float {
        val metrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
    }

    /**
     * dpToPx
     */
    fun spToPx(resources: Resources, sp: Float): Float {
        val metrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics)
    }

}
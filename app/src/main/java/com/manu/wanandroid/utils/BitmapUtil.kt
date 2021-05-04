package com.manu.wanandroid.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.RawRes
import kotlin.math.roundToInt


/**
 * @Desc: BitmapUtil
 * @Author: jzman
 */
object BitmapUtil {

    private const val TAG = "BitmapUtil"

    /**
     * 位图采样
     * @param resId 资源id
     * @param reqWidth 显示宽
     * @param reqHeight 显示高
     * @return 返回重新采样后的Bitmap
     */
    fun decodeSampleFromBitmap(
        context: Context,
        @RawRes resId: Int,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        val resource = context.resources
        //创建一个位图工厂的设置选项
        val options = BitmapFactory.Options()
        //设置该属性为true,解码时只能获取width、height、mimeType
        options.inJustDecodeBounds = true
        //解码
        BitmapFactory.decodeResource(resource, resId, options)
        //计算采样比例
        options.inSampleSize = calculateSampleSize(options, reqWidth, reqHeight)
        val inSampleSize = options.inSampleSize
        //设置该属性为false，实现真正解码
        options.inJustDecodeBounds = false
        //解码
        return BitmapFactory.decodeResource(resource, resId, options)
    }

    /**
     * 计算位图采样比例
     *
     * @param option BitmapFactory.Options
     * @param reqWidth 显示宽
     * @param reqHeight 显示高
     * @return 返回采样大小
     */
    private fun calculateSampleSize(
        option: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        //获得图片的原宽高
        val width = option.outWidth
        val height = option.outHeight
        var inSampleSize = 1
        if (width > reqWidth || height > reqHeight) {
            inSampleSize = if (width > height) {
                (height.toFloat() / reqHeight.toFloat()).roundToInt()
            } else {
                (width.toFloat() / reqWidth.toFloat()).roundToInt()
            }
        }
        return inSampleSize
    }
}
package com.manu.wanandroid.utils

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService

/**
 * @Desc:
 * @Author: jzman
 */
object KeyBoardUtil {
    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(event: MotionEvent, view: View?, activity: Activity) {
        try {
            if (view != null && view is EditText) {
                val location = intArrayOf(0, 0)
                view.getLocationInWindow(location)
                val left = location[0]
                val top = location[1]
                val right = left + view.getWidth()
                val bottom = top + view.getHeight()
                if (event.rawX < left.toFloat() || event.rawX > right.toFloat() || event.y < top.toFloat() || event.rawY > bottom.toFloat()) {
                    val token = view.getWindowToken()
                    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(token, 2)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 如果输入法在窗口上已经显示，则隐藏，反之则显示
     */
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
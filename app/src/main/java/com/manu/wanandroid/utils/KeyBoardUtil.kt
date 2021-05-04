package com.manu.wanandroid.utils

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * @Desc:
 * @Author: jzman
 */
object KeyBoardUtil {
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
}
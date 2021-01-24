package com.manu.wanandroid.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView

/**
 * @Desc: 支持双击的WebView
 * @Author: jzman
 */
class MWebView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    private var firstClickTime = 0L
    private lateinit var onDoubleClickListener: OnDoubleClickListener
    fun setOnDoubleClickListener(listener: OnDoubleClickListener) {
        this.onDoubleClickListener = listener
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            if (System.currentTimeMillis() - firstClickTime <= DOUBLE_INTERVAL) {
                onDoubleClickListener.onDoubleClick(this)
            }
            firstClickTime = System.currentTimeMillis()
            performClick()
        }
        return super.onTouchEvent(event)
    }

    companion object {
        private const val DOUBLE_INTERVAL = 200
    }

    interface OnDoubleClickListener {
        fun onDoubleClick(v: View?)
    }

}
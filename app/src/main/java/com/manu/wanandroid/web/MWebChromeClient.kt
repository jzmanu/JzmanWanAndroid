package com.manu.wanandroid.web

import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.widget.ContentLoadingProgressBar
import com.manu.wanandroid.utils.L

private const val TAG = "MWebChromeClient"
/**
 * @Desc: WebChromeClient
 * @Author: jzman
 * @Date: 2020/11/6 0:45.
 */
class MWebChromeClient(var progressBar: ContentLoadingProgressBar) : WebChromeClient() {
    /**
     * 加载进度监听
     */
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        L.i(TAG,"onProgressChanged > url:$newProgress")
        progressBar.progress = newProgress
        if(newProgress == 100) progressBar.hide()
    }
}
package com.manu.wanandroid.web

import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import com.manu.wanandroid.utils.L

private const val TAG = "MWebViewClient"

/**
 * @Desc: MWebViewClient
 * @Author: jzman
 * @Date: 2020/11/4 23:45.
 */
class MWebViewClient : WebViewClient() {

    /**
     * 加载网页需要重定向的时候回调
     */
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean{
        L.i(TAG,"shouldOverrideUrlLoading > url:$url")
        val uri:Uri = Uri.parse(url)
        return when (uri.scheme){
            "jianshu" -> true
            else -> false
        }
    }

}
package com.manu.wanandroid.web

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.manu.wanandroid.app.App
import com.manu.wanandroid.nice.NiceArticleFactory
import com.manu.wanandroid.utils.L
import java.io.ByteArrayInputStream

private const val TAG = "MWebViewClient"

/**
 * @Desc: MWebViewClient
 * @Author: jzman
 * @Date: 2020/11/4 23:45.
 */
class MWebViewClient(var agent: String) : WebViewClient() {

    /**
     * 加载网页需要重定向的时候回调
     */
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        L.i(TAG, "shouldOverrideUrlLoading > url:$url")
        val uri: Uri = Uri.parse(url)
        return when (uri.scheme) {
            "jianshu" -> true
            "bilibili" -> {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                App.getApp().baseContext.startActivity(intent)
                true
            }
            else -> false
        }
    }

    /**
     * 通知加载一个资源请求，是否进行拦截
     */
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        L.i(TAG, "shouldInterceptRequest > ${request?.url}, ${request?.method}")
        val uri = request?.url ?: return super.shouldInterceptRequest(view, request)
        val url = uri.toString()
        val content = NiceArticleFactory().create(NiceArticleFactory.jianshu)?.nice(url, agent, request)
        return if (content.isNullOrEmpty())
            super.shouldInterceptRequest(view, request)
        else
            WebResourceResponse("text/html", "utf-8", ByteArrayInputStream(content.toByteArray()))
    }

}
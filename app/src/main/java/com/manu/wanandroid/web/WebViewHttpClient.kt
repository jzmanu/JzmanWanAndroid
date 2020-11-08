package com.manu.wanandroid.web

import android.webkit.WebResourceRequest
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @Desc: WebViewHttpClient
 * @Author: jzman
 * @Date: 2020/11/8 2:03.
 */
class WebViewHttpClient {
    companion object{
        @JvmStatic
        fun request(url:String, agent:String,request:WebResourceRequest):String?{
            val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
            val headers: Map<String, String> = request.requestHeaders
            val requestBuilder = Request.Builder().url(url)
            headers.forEach {
                requestBuilder.addHeader(it.key, it.value)
            }
            requestBuilder.addHeader("USER-AGENT", agent)
            requestBuilder.method(request.method, null)
            val response = okHttpClient.newCall(requestBuilder.build()).execute()
            return response.body()?.string()
        }
    }
}
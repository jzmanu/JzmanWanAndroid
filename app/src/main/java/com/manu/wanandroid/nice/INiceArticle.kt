package com.manu.wanandroid.nice

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse

/**
 * @Desc: 文章拦截器
 * @Author: jzman
 * @Date: 2020/11/6 22:52.
 */
interface INiceArticle {
    fun nice(url:String, agent:String,request:WebResourceRequest): String?
}
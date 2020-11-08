package com.manu.wanandroid.nice

import android.webkit.WebResourceRequest
import com.manu.wanandroid.web.WebViewHttpClient

/**
 * @Desc: 简书文章美化
 * @Author: jzman
 * @Date: 2020/11/6 22:57.
 */
class NiceJianShuArticleIml:INiceArticle {
    override fun nice(url:String, agent:String,request:WebResourceRequest): String? {
        if (!url.startsWith("https://www.jianshu.com/p")) return null
        val content = WebViewHttpClient.request(url,agent,request)
        val nice = ".header-wrap {display: none;}.collapse-tips{display: none;}.call-app-btn{display: none;}" +
                ".collapse-free-content {height: auto !important;}#recommended-notes{display: none;}#footer{display: none;}" +
                ".note-graceful-button{display: none;}#free-reward-panel{display: none;}#comment-main{display: none;}" +
                ".note-comment-above-ad-wrap{display: none;}</style>"
        return content?.replace("</style>",nice)
    }
}
package com.manu.wanandroid.nice

import android.content.Context

/**
 * @Desc: ArticleInterceptorFactory
 * @Author: jzman
 * @Date: 2020/11/9 16:33.
 */
class NiceArticleFactory {
    companion object{
        @JvmStatic val jianshu = "jianshu"
    }

    fun create(type: String):INiceArticle? {
        when(type){
            jianshu -> return NiceJianShuArticleIml()
        }
        return null
    }
}
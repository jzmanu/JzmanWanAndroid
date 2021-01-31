package com.manu.wanandroid.contract.home

import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.mvp.presenter.IPresenter
import com.manu.wanandroid.mvp.view.IView

/**
 * @Desc: 我的分享
 * @Author: jzman
 * @Date: 2021/1/31 18:20.
 */
interface ShareContract {
    interface View : IView{
        fun onGetShareArticleSuccess(list: List<Article>)
    }

    interface Presenter : IPresenter<View>{
        fun getShareArticle(pageIndex:Int)
    }
}
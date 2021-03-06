package com.manu.wanandroid.contract.home

import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.base.mvp.presenter.IPresenter
import com.manu.wanandroid.base.mvp.view.IView

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
        fun getMineShareArticle(pageIndex:Int)
    }
}
package com.manu.wanandroid.contract.search

import com.manu.wanandroid.base.mvp.presenter.IPresenter
import com.manu.wanandroid.base.mvp.view.IView
import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.bean.HotWord

/**
 * @Desc: SearchContract
 * @Author: jzman
 */
interface SearchContract {
    interface View : IView {
        fun onHotWordsSuccess(result:List<HotWord>)
        fun onSearchSuccess(result:List<Article>)
    }

    interface Presenter : IPresenter<View> {
        fun hotWords()
        fun search(pageIndex:Int, key:String?)
    }

}
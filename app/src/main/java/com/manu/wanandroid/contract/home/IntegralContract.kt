package com.manu.wanandroid.contract.home

import com.manu.wanandroid.base.mvp.presenter.IPresenter
import com.manu.wanandroid.base.mvp.view.IView
import com.manu.wanandroid.bean.Integral

/**
 * @Desc: IntegralContract
 * @Author: jzman
 * @Date: 2021/2/6 .
 */
interface IntegralContract {
    interface View : IView{
        fun onGetMineIntegralSuccess(result:List<Integral>)
    }

    interface Presenter : IPresenter<View>{
        fun getMineIntegralArticle(pageIndex:Int)
    }
}
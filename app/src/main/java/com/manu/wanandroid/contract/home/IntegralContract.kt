package com.manu.wanandroid.contract.home

import com.manu.wanandroid.base.mvp.presenter.IPresenter
import com.manu.wanandroid.base.mvp.view.IView
import com.manu.wanandroid.bean.Integral
import com.manu.wanandroid.bean.IntegralInfo

/**
 * @Desc: IntegralContract
 * @Author: jzman
 */
interface IntegralContract {
    interface View : IView {
        fun onGetMineIntegralSuccess(result: List<Integral>)
        fun onGetMineIntegralInfoSuccess(result: IntegralInfo)
    }

    interface Presenter : IPresenter<View> {
        fun getMineIntegral(pageIndex: Int)
        fun getMineIntegralInfo()
    }
}
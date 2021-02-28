package com.manu.wanandroid.presenter.home

import com.manu.wanandroid.bean.Integral
import com.manu.wanandroid.http.rx.BaseObserver
import com.manu.wanandroid.http.rx.BasePageBean
import com.manu.wanandroid.http.rx.RxUtil
import com.manu.wanandroid.model.DataManager
import com.manu.wanandroid.base.mvp.presenter.BasePresenter
import com.manu.wanandroid.contract.home.IntegralContract
import javax.inject.Inject

/**
 * @Desc: MineIntegralPresenter
 * @Author: jzman
 * @Date: 2021/2/6 .
 */
class MineIntegralPresenter @Inject constructor(mDataManager: DataManager?) : BasePresenter<IntegralContract.View>(mDataManager), IntegralContract.Presenter {

    override fun getMineIntegralArticle(pageIndex: Int) {
        addRxSubscribe(mDataManager.mineIntegral(pageIndex)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(object : BaseObserver<BasePageBean<Integral>>(mView){
                    override fun onNext(result: BasePageBean<Integral>) {
                        mView.onGetMineIntegralSuccess(result.datas)
                    }
                }))
    }
}
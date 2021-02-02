package com.manu.wanandroid.presenter.home

import com.manu.wanandroid.bean.Integral
import com.manu.wanandroid.contract.home.ShareContract
import com.manu.wanandroid.http.rx.BaseObserver
import com.manu.wanandroid.http.rx.BasePageBean
import com.manu.wanandroid.http.rx.RxUtil
import com.manu.wanandroid.mvp.model.DataManager
import com.manu.wanandroid.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * @Desc: 我的积分
 * @Author: jzman
 * @Date: 2021/2/1
 */
class MineIntegralPresenter @Inject constructor(mDataManager: DataManager?) : BasePresenter<ShareContract.View>(mDataManager), ShareContract.Presenter {
    override fun getShareArticle(pageIndex: Int) {
        addRxSubscribe(mDataManager.mineIntegral(pageIndex)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(object : BaseObserver<BasePageBean<Integral>>(mView){
                    override fun onNext(result: BasePageBean<Integral>) {

                    }
                }))
    }
}
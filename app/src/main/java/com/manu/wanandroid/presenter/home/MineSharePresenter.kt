package com.manu.wanandroid.presenter.home

import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.contract.home.ShareContract
import com.manu.wanandroid.bean.Share
import com.manu.wanandroid.http.rx.BaseObserver
import com.manu.wanandroid.http.rx.RxUtil
import com.manu.wanandroid.mvp.model.DataManager
import com.manu.wanandroid.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * @Desc: 我的分享
 * @Author: jzman
 * @Date: 2021/1/31 18:25.
 */
class MineSharePresenter @Inject constructor(mDataManager: DataManager?) : BasePresenter<ShareContract.View>(mDataManager), ShareContract.Presenter {
    override fun getShareArticle(pageIndex: Int) {
        addRxSubscribe(mDataManager.mineShareArticle(pageIndex)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(object : BaseObserver<Share<Article>>(mView){
                    override fun onNext(result: Share<Article>) {
                        mView.onGetShareArticleSuccess(result.shareArticles.datas)
                    }
                }))
    }
}
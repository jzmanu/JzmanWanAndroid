package com.manu.wanandroid.presenter.home;

import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.contract.home.CollectContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.model.DataManager;
import com.manu.wanandroid.base.mvp.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * @Desc: CollectPresenter
 * @Author: jzman
 * @Date: 2019/5/16.
 */
public class MineCollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {
    @Inject
    public MineCollectPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void collectArticle(String id) {
        addRxSubscribe(mDataManager.collectArticle(id)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<Object>(mView) {
                    @Override
                    public void onNext(@NotNull Object o) {
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        mView.onCollectArticleSuccess();
                    }
                })
        );
    }

    @Override
    public void unCollectArticle(String id) {
        addRxSubscribe(mDataManager.unCollectArticle(id)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<Object>(mView) {
                    @Override
                    public void onNext(Object o) {
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        mView.onUnCollectArticleSuccess();
                    }
                })
        );
    }

    @Override
    public void getCollectArticle(int pageIndex) {
        addRxSubscribe(mDataManager.mineCollectArticle(pageIndex)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<BasePageBean<Collect>>(mView) {
                    @Override
                    public void onNext(@NonNull BasePageBean<Collect> result) {
                        mView.onGetCollectArticleSuccess(result.getDatas());
                    }
                }));
    }
}

package com.manu.wanandroid.base.mvp.presenter;

import com.manu.wanandroid.model.DataManager;
import com.manu.wanandroid.base.mvp.view.IView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Desc: BasePresenter
 * @Author: jzman
 * @Date: 2019/5/7 0007 15:56
 */
public class BasePresenter<T extends IView> implements IPresenter<T> {
    protected T mView;
    protected DataManager mDataManager;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void onAttachView(T view) {
        this.mView = view;
    }

    @Override
    public void onDetachView() {
        if (mView != null) mView = null;
    }

    @Override
    public boolean isAttachView() {
        return mView == null;
    }

    @Override
    public void addRxSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
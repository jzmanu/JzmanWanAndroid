package com.manu.wanandroid.base.mvp.presenter;

import com.manu.wanandroid.base.mvp.view.IView;

import io.reactivex.disposables.Disposable;

/**
 * @Desc: IPresenter
 * @Author: jzman
 * @Date: 2019/5/7 0007 15:51
 */
public interface IPresenter<T extends IView> {
    void onAttachView(T view);

    void onDetachView();

    boolean isAttachView();

    void addRxSubscribe(Disposable disposable);
}
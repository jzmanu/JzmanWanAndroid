package com.manu.wanandroid.mvp.view;

/**
 * @Desc: IView
 * @Author: jzman
 * @Date: 2019/5/7 0007 15:36
 */
public interface IView {
    void onShowLoading();

    void onHideLoading();

    void onShowNormalContent();

    void onHideNormalContent();

    void onShowErrorMessage(String message);

    void onShowEmptyMessage();

}



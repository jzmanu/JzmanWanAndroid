package com.manu.wanandroid.base.activity;

import android.view.View;
import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.http.exception.MException;
import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.mvp.view.IView;
import com.manu.wanandroid.widget.MessageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: BaseMvpActivity
 * @Author: jzman
 * @Date: 2019/5/8 0008 16:15
 */
@SuppressWarnings("all")
public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity implements IView {
    protected T mPresenter;

    @Override
    public void onAttach() {
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowErrorMessage(String message) {

    }

    @Override
    public void onShowNormalContent() {

    }

    @Override
    public void onHideNormalContent() {

    }

    @Override
    public void onShowEmptyMessage() {

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}

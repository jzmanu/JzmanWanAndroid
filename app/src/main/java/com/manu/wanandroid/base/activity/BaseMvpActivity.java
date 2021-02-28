package com.manu.wanandroid.base.activity;

import com.manu.wanandroid.base.mvp.presenter.IPresenter;
import com.manu.wanandroid.base.mvp.view.IView;

/**
 * @Desc: BaseMvpActivity
 * @Author: jzman
 * @Date: 2019/5/8 0008 16:15
 */
@SuppressWarnings("all")
public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity implements IView {
    protected T mPresenter;

    protected abstract T onPresenter();

    @Override
    public void onAttach() {
        mPresenter = onPresenter();
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

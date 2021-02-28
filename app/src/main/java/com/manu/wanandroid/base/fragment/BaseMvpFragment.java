package com.manu.wanandroid.base.fragment;

import android.view.View;

import com.manu.wanandroid.base.mvp.presenter.IPresenter;
import com.manu.wanandroid.base.mvp.view.IView;
import com.manu.wanandroid.widget.MessageView;

import java.util.List;

/**
 * @Desc: BaseMvpFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:48
 */
@SuppressWarnings("all")
public abstract class BaseMvpFragment<T extends IPresenter> extends BaseFragment implements IView {
    protected abstract T onPresenter();

    T mPresenter;
    MessageView mMessageView;
    List<View> mContentViews;

    @Override
    public void onAttach() {
        super.onAttach();
        mPresenter = onPresenter();
        if (mPresenter != null) mPresenter.onAttachView(this);
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNormalContent() {
    }

    @Override
    public void onHideNormalContent() {
    }

    @Override
    public void onShowErrorMessage(String message) {
    }

    @Override
    public void onShowEmptyMessage() {
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}

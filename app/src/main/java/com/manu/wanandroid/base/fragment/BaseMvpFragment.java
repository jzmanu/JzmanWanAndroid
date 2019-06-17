package com.manu.wanandroid.base.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.manu.wanandroid.R;
import com.manu.wanandroid.http.exception.MException;
import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.mvp.view.IView;
import com.manu.wanandroid.widget.MessageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
//        setNormalContentView(true);
    }

    @Override
    public void onHideNormalContent() {
//        setNormalContentView(false);
    }

    @Override
    public void onShowErrorMessage(String message) {
//        setNormalContentView(false);
//        mMessageView.showErrorMessage(message);
    }

    @Override
    public void onShowEmptyMessage() {
//        setNormalContentView(false);
//        mMessageView.showEmptyMessage();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

//    private void initNormalContentView(ViewGroup viewGroup) {
//        int size = viewGroup.getChildCount();
//        for (int i = 0; i < size; i++) {
//            View view = viewGroup.getChildAt(i);
//            if (view instanceof MessageView) {
//
//            } else {
//                mContentViews.add(view);
//            }
//        }
//    }
//
//    private void setNormalContentView(boolean isVisable) {
//        int size = mContentViews.size();
//        for (int i = 0; i < size; i++) {
//            if (isVisable) {
//                mContentViews.get(i).setVisibility(View.VISIBLE);
//                mMessageView.setVisibility(View.GONE);
//            } else {
//                mContentViews.get(i).setVisibility(View.GONE);
//                mMessageView.setVisibility(View.VISIBLE);
//            }
//        }
//    }
}

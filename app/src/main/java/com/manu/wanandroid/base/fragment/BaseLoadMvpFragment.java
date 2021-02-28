package com.manu.wanandroid.base.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.http.exception.MException;
import com.manu.wanandroid.base.mvp.presenter.IPresenter;
import com.manu.wanandroid.base.mvp.view.IView;
import com.manu.wanandroid.widget.MessageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: BaseMvpFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:48
 */
@SuppressWarnings("all")
public abstract class BaseLoadMvpFragment<T extends IPresenter> extends BaseFragment implements IView {
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
    public void onInitMessageView() {
        mContentViews = new ArrayList<>();

        if (getView() == null) {
            return;
        }

        ViewGroup viewGroup = getView().findViewById(R.id.normal_view);

        if (viewGroup == null) {
            throw new MException("You must have a ViewGroup that viewId is normal_view in this layout.");
        }

        View view = viewGroup.getChildAt(0);
        ViewGroup content = null;
        if (view instanceof ViewGroup) {
            content = (ViewGroup) view;
        }

        if (content == null) {
            throw new MException("You must add a ViewGroup that viewId is normal_view in this layout to refresh success.");
        }

        mMessageView = new MessageView(getActivity());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mMessageView.setLayoutParams(layoutParams);
        mMessageView.setVisibility(View.GONE);
        content.addView(mMessageView);
        initNormalContentView(content);
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNormalContent() {
        setNormalContentView(true);
    }

    @Override
    public void onHideNormalContent() {
        setNormalContentView(false);
    }

    @Override
    public void onShowErrorMessage(String message) {
        setNormalContentView(false);
        mMessageView.showErrorMessage(message);
    }

    @Override
    public void onShowEmptyMessage() {
        setNormalContentView(false);
        mMessageView.showEmptyMessage();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    private void initNormalContentView(ViewGroup viewGroup) {
        int size = viewGroup.getChildCount();
        for (int i = 0; i < size; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof MessageView) {

            } else {
                mContentViews.add(view);
            }
        }
    }

    private void setNormalContentView(boolean isVisable) {
        int size = mContentViews.size();
        for (int i = 0; i < size; i++) {
            if (isVisable) {
                mContentViews.get(i).setVisibility(View.VISIBLE);
                mMessageView.setVisibility(View.GONE);
            } else {
                mContentViews.get(i).setVisibility(View.GONE);
                mMessageView.setVisibility(View.VISIBLE);
            }
        }
    }
}

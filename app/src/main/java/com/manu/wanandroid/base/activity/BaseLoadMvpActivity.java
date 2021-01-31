package com.manu.wanandroid.base.activity;

import android.view.View;
import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.http.exception.MException;
import com.manu.wanandroid.mvp.presenter.BasePresenter;
import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.widget.MessageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: BaseLoadMvpActivity
 * @Author: jzman
 * @Date: 2019/5/20 0020 10:02
 */
@SuppressWarnings("all")
public abstract class BaseLoadMvpActivity<T extends IPresenter> extends BaseMvpActivity<T> {
    private MessageView mMessageView;
    private List<View> mContentViews;

    @Override
    public void onInitMessageView() {
        mContentViews = new ArrayList<>();

        ViewGroup viewGroup = findViewById(R.id.normal_view);

        if (viewGroup == null) {
            throw new MException("You must have a TabView that viewId is normal_view in this layout.");
        }

        View view = viewGroup.getChildAt(0);
        ViewGroup content = null;
        if (view instanceof ViewGroup) {
            content = (ViewGroup) view;
        }

        if (content == null) {
            throw new MException("You must add a ViewGroup that viewId is normal_view in this layout to refresh success.");
        }

        mMessageView = new MessageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mMessageView.setLayoutParams(layoutParams);
        content.removeView(mMessageView);
        content.addView(mMessageView);
        initNormalContentView(content);
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

    private void setNormalContentView(boolean isVisible) {
        int size = mContentViews.size();
        for (int i = 0; i < size; i++) {
            if (isVisible) {
                mContentViews.get(i).setVisibility(View.VISIBLE);
            } else {
                mContentViews.get(i).setVisibility(View.GONE);
            }
        }
    }
}

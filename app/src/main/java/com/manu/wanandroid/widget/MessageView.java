package com.manu.wanandroid.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.manu.wanandroid.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Desc: MessageView
 * @Author: jzman
 * @Date: 2019/5/16 0016 16:08
 */
public class MessageView extends FrameLayout {

    private ImageView mIvMessageView;
    private TextView mTvMessageView;

    public MessageView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MessageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_view, this, true);
        mIvMessageView = view.findViewById(R.id.iv_message);
        mTvMessageView = view.findViewById(R.id.tv_message);
    }

    public void showErrorMessage(String message) {
        showMessage(true);
        mIvMessageView.setBackgroundResource(R.drawable.ic_data_error_128dp);
        mTvMessageView.setText(TextUtils.isEmpty(message) ? "未知错误" : message);
    }

    public void showNetErrorMessage(String message) {
        showMessage(true);
        mIvMessageView.setBackgroundResource(R.drawable.ic_net_error_128dp);
        mTvMessageView.setText(TextUtils.isEmpty(message) ? "网络连接错误" : message);
    }

    public void showNetErrorMessage() {
        showMessage(true);
        mIvMessageView.setBackgroundResource(R.drawable.ic_net_error_128dp);
        mTvMessageView.setText("网络连接错误");
    }

    public void showEmptyMessage() {
        showMessage(true);
        mIvMessageView.setBackgroundResource(R.drawable.ic_data_empty_128dp);
        mTvMessageView.setText("没有数据");
    }

    public void hideDataMessage() {
        showMessage(false);
    }

    private void showMessage(boolean isShow) {
        if (isShow) {
            mIvMessageView.setVisibility(VISIBLE);
            mTvMessageView.setVisibility(VISIBLE);
        } else {
            mIvMessageView.setVisibility(GONE);
            mTvMessageView.setVisibility(GONE);
        }
    }
}

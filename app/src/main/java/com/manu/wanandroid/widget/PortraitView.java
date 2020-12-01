package com.manu.wanandroid.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.bumptech.glide.RequestManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Desc: 圆形头像
 * @Author: jzman
 */
public class PortraitView extends CircleImageView {
    public PortraitView(Context context) {
        super(context);
    }

    public PortraitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PortraitView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void setPortrait(RequestManager manager,String url) {
        if (TextUtils.isEmpty(url)) url = "";
        manager.load(url)
                .centerCrop()
                .dontAnimate() // CircleImageView设置动画可能出问题
                .into(this);
    }
}

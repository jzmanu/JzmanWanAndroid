package com.manu.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @Desc: GlideImageLoader
 * @Author: jzman
 * @Date: 2019/5/13 0013 15:30
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String url = (String) path;
        Glide.with(context).load(url).into(imageView);
    }
}

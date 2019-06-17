package com.manu.wanandroid.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @Desc: ImageLoaderImpl
 * @Author: jzman
 * @Date: 2019/5/30 0030 14:21
 */
public class ImageLoaderImpl implements IImageLoader {

    @Override
    public void showImageForNet(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}

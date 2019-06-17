package com.manu.wanandroid.utils.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @Desc: IImageLoader
 * @Author: jzman
 * @Date: 2019/5/30 0030 14:20
 */
public interface IImageLoader {
    void showImageForNet(Context context, String url, ImageView imageView);
}

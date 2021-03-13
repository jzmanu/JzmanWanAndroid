package com.manu.wanandroid.utils.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @Desc: ImageLoaderHelper
 * @Author: jzman
 * @Date: 2019/5/30 0030 14:41
 */
public class ImageLoaderHelper {
    public static ImageLoaderHelper getInstance() {
        return Holder.INSTANCE;
    }

    public void showImageForNet(Context context, String url, ImageView imageView) {
        IImageLoader mImageLoader = new ImageLoaderImpl();
        mImageLoader.showImageForNet(context, url, imageView);
    }

    static class Holder {
        static ImageLoaderHelper INSTANCE = new ImageLoaderHelper();
    }


}

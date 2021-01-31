package com.manu.wanandroid.di.module.activity;

import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.home.adapter.CollectArticleAdapter;
import com.manu.wanandroid.ui.home.adapter.ShareArticleAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: 
 * @Author: jzman
 * @Date: 2021/1/30 .
 */
@Module
public class MineShareActivityModule {
    @PreActivity
    @Provides
    ShareArticleAdapter providerShareArticleAdapter(){
        return new ShareArticleAdapter();
    }
}

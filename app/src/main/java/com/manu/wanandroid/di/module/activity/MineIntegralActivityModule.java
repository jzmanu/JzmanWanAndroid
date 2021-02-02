package com.manu.wanandroid.di.module.activity;

import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.home.adapter.IntegralArticleAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: 
 * @Author: jzman
 * @Date: 2021/1/30 .
 */
@Module
public class MineIntegralActivityModule {
    @PreActivity
    @Provides
    IntegralArticleAdapter providerIntegralArticleAdapter(){
        return new IntegralArticleAdapter();
    }
}

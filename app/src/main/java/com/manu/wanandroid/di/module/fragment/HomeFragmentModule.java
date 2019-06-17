package com.manu.wanandroid.di.module.fragment;

import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.home.adapter.HomeArticleAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: HomeFragmentModule
 * @Author: jzman
 * @Date: 2019/5/10 0010 13:38
 */
@Module
public class HomeFragmentModule {

    @PreFragment
    @Provides
    HomeArticleAdapter providerHomeArticleAdapter(){
        return new HomeArticleAdapter();
    }
}

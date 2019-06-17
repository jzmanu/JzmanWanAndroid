package com.manu.wanandroid.di.module.fragment;

import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.home.adapter.HomeArticleAdapter;
import com.manu.wanandroid.ui.ks.adapter.KsCategoryArticleAdapter;
import com.manu.wanandroid.ui.ks.bean.KsBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: KsRightChildFragmentModule
 * @Author: jzman
 * @Date: 2019/5/31 0031 13:24
 */
@Module
public class KsRightChildFragmentModule {
    @PreFragment
    @Provides
    KsCategoryArticleAdapter providerKsCategoryArticleAdapter() {
        return new KsCategoryArticleAdapter();
    }
}

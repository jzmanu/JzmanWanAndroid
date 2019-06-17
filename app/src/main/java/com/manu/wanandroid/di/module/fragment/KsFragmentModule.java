package com.manu.wanandroid.di.module.fragment;

import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.ks.adapter.KsCategoryAdapter;
import com.manu.wanandroid.ui.ks.fragment.KsRightFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: KsFragmentModule
 * @Author: jzman
 * @Date: 2019/5/31 0031 13:24
 */
@Module
public class KsFragmentModule {
    @PreFragment
    @Provides
    KsCategoryAdapter providerKsCategoryAdapter() {
        return new KsCategoryAdapter();
    }

    @PreFragment
    @Provides
    KsRightFragment providerKsRightFragment() {
        return new KsRightFragment();
    }
}

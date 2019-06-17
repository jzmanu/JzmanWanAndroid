package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.KsFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.ks.fragment.KsFragment;

import dagger.Subcomponent;

/**
 * @Desc: KsFragmentComponent
 * @Author: jzman
 * @Date: 2019/5/31 0031 13:24
 */
@PreFragment
@Subcomponent(modules = KsFragmentModule.class)
public interface KsFragmentComponent {
    void injectKsFragment(KsFragment ksFragment);
}

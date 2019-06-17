package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.KsRightChildFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.ks.fragment.KsRightChildFragment;

import dagger.Subcomponent;

/**
 * @Desc: KsRightChildFragmentComponent
 * @Author: jzman
 * @Date: 2019/5/31 0031 13:24
 */
@PreFragment
@Subcomponent(modules = KsRightChildFragmentModule.class)
public interface KsRightChildFragmentComponent {
    void injectKsRightArticleFragment(KsRightChildFragment ksRightFragment);
}

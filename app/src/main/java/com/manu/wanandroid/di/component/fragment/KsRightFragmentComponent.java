package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.KsRightFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.ks.fragment.KsRightFragment;

import dagger.Subcomponent;

/**
 * @Desc: KsRightFragmentComponent
 * @Author: jzman
 * @Date: 2019/6/4 0004 10:47
 */
@PreFragment
@Subcomponent(modules = KsRightFragmentModule.class)
public interface KsRightFragmentComponent {
    void injectKsFragment(KsRightFragment ksRightFragment);
}

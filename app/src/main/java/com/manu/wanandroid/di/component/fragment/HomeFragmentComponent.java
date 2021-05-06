package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.HomeFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.main.fragment.HomeFragment;

import dagger.Subcomponent;

/**
 * @Desc: HomeFragmentComponent
 * @Author: jzman
 * @Date: 2019/5/10 0010 13:54
 */
@PreFragment
@Subcomponent(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {
    void injectHomeFragment(HomeFragment homeFragment);
}

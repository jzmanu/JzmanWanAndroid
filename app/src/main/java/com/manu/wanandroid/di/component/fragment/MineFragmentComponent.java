package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.MineFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.main.fragment.MineFragment;

import dagger.Subcomponent;

/**
 * @Desc: HomeFragmentComponent
 * @Author: jzman
 */
@PreFragment
@Subcomponent(modules = MineFragmentModule.class)
public interface MineFragmentComponent {
    void injectMineFragment(MineFragment mineFragment);
}

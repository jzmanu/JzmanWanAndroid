package com.manu.wanandroid.di.component.activity;

import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.module.activity.MineShareActivityModule;
import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.home.activity.MineShareActivity;

import dagger.Component;

/**
 * @Desc:
 * @Author: jzman
 * @Date: 2021/1/30 .
 */
@PreActivity
@Component(dependencies = AppComponent.class, modules = MineShareActivityModule.class)
public interface MineShareActivityComponent {
    void injectMineShareActivity(MineShareActivity activity);
}

package com.manu.wanandroid.di.component.activity;

import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.module.activity.MineCollectActivityModule;
import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.home.activity.MineCollectActivity;

import dagger.Component;

/**
 * @Desc:
 * @Author: jzman
 * @Date: 2021/1/30 .
 */
@PreActivity
@Component(dependencies = AppComponent.class, modules = MineCollectActivityModule.class)
public interface MineCollectActivityComponent {
    void injectMineCollectActivity(MineCollectActivity activity);
}

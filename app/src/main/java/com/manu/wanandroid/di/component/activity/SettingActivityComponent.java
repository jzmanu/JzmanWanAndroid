package com.manu.wanandroid.di.component.activity;

import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.module.activity.ArticleDetailModule;
import com.manu.wanandroid.di.module.activity.SettingActivityModule;
import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.home.activity.SettingActivity;

import dagger.Component;

/**
 * @Desc: SettingActivityComponent
 * @Author: jzman
 */
@PreActivity
@Component(dependencies = AppComponent.class,modules = SettingActivityModule.class)
public interface SettingActivityComponent {
    void injectSettingActivity(SettingActivity settingActivity);
}

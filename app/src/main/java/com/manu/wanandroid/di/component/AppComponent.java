package com.manu.wanandroid.di.component;

import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.di.module.AppModule;
import com.manu.wanandroid.mvp.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Desc: AppComponent
 * @Author: jzman
 * @Date: 2019/5/8 0008 14:27
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void injectApp(MApplication application);
    // for dependencies component use.
    DataManager getDataManager();
}

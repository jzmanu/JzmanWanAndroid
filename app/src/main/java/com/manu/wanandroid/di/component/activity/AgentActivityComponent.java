package com.manu.wanandroid.di.component.activity;

import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.module.activity.AgentActivityModule;
import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.main.activity.AgentActivity;

import dagger.Component;

/**
 * @Desc:
 * @Author: jzman
 * @Date: 2021/1/19 22:22.
 */
@PreActivity
@Component(dependencies = AppComponent.class,modules = AgentActivityModule.class)
public interface AgentActivityComponent {
    void injectAgentActivity(AgentActivity activity);
}
package com.manu.wanandroid.di.component.activity;

import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.component.fragment.HomeFragmentComponent;
import com.manu.wanandroid.di.component.fragment.KsFragmentComponent;
import com.manu.wanandroid.di.component.fragment.KsRightChildFragmentComponent;
import com.manu.wanandroid.di.component.fragment.KsRightFragmentComponent;
import com.manu.wanandroid.di.component.fragment.MineFragmentComponent;
import com.manu.wanandroid.di.component.fragment.ProjectChildFragmentComponent;
import com.manu.wanandroid.di.component.fragment.ProjectFragmentComponent;
import com.manu.wanandroid.di.module.activity.MainActivityModule;
import com.manu.wanandroid.di.module.fragment.HomeFragmentModule;
import com.manu.wanandroid.di.module.fragment.KsFragmentModule;
import com.manu.wanandroid.di.module.fragment.KsRightChildFragmentModule;
import com.manu.wanandroid.di.module.fragment.KsRightFragmentModule;
import com.manu.wanandroid.di.module.fragment.MineFragmentModule;
import com.manu.wanandroid.di.module.fragment.ProjectChildFragmentModule;
import com.manu.wanandroid.di.module.fragment.ProjectFragmentModule;
import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.main.activity.MainActivity;

import dagger.Component;

/**
 * @Desc: MainActivityComponent
 * @Author: jzman
 * @Date: 2019/5/9 0009 16:02
 */
@PreActivity
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void injectMainActivity(MainActivity mainActivity);
    HomeFragmentComponent getHomeFragmentComponent(HomeFragmentModule homeFragmentModule);
    MineFragmentComponent getMineFragmentComponent(MineFragmentModule mineFragmentModule);
    ProjectFragmentComponent getProjectFragmentComponent(ProjectFragmentModule projectFragmentModule);
    ProjectChildFragmentComponent getProjectChildFragmentComponent(ProjectChildFragmentModule childFragmentModule);
    KsFragmentComponent getKsFragmentComponent(KsFragmentModule ksFragmentModule);
    KsRightFragmentComponent getKsRightFragmentComponent(KsRightFragmentModule ksRightFragmentModule);
    KsRightChildFragmentComponent getKsRightChildFragment(KsRightChildFragmentModule ksRightChildFragmentModule);
}

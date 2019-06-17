package com.manu.wanandroid.di.module.activity;

import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.home.fragment.HomeFragment;
import com.manu.wanandroid.ui.ks.fragment.KsFragment;
import com.manu.wanandroid.ui.project.fragment.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

/**
 * @Desc: MainActivityModule
 * @Author: jzman
 * @Date: 2019/5/9 0009 15:24
 */
@Module
public class MainActivityModule {
    @PreActivity
    @Provides
    HomeFragment providerHomeFragment() {
        return new HomeFragment();
    }

    @PreActivity
    @Provides
    ProjectFragment providerProjectFragment() {
        return new ProjectFragment();
    }

    @PreActivity
    @Provides
    KsFragment providerKsFragment() {
        return new KsFragment();
    }

    @PreActivity
    @Provides
    List<Fragment> providerFragments() {
        return new ArrayList<>();
    }
}

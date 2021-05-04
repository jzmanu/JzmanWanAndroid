package com.manu.wanandroid.di.module.activity;

import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.search.fragment.SearchFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: SearchActivityModule
 * @Author: jzman
 */
@Module
public class SearchActivityModule {

    @PreActivity
    @Provides
    SearchFragment providerSearchFragment() {
        return new SearchFragment();
    }
}

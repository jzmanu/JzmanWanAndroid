package com.manu.wanandroid.di.component.activity;

import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.component.fragment.SearchFragmentComponent;
import com.manu.wanandroid.di.module.activity.SearchActivityModule;
import com.manu.wanandroid.di.module.fragment.SearchFragmentModule;
import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.search.activity.SearchActivity;

import dagger.Component;

/**
 * @Desc:
 * @Author: jzman
 */
@PreActivity
@Component(dependencies = AppComponent.class, modules = SearchActivityModule.class)
public interface SearchActivityComponent {
    void injectSearchActivity(SearchActivity activity);
    SearchFragmentComponent getSearchFragmentComponent(SearchFragmentModule searchFragmentModule);
}

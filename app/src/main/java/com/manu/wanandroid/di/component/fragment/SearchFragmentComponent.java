package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.SearchFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.search.fragment.SearchFragment;

import dagger.Subcomponent;

/**
 * @Desc: SearchFragmentComponent
 * @Author: jzman
 */
@PreFragment
@Subcomponent(modules = SearchFragmentModule.class)
public interface SearchFragmentComponent {
    void injectSearchFragment(SearchFragment searchFragment);
}

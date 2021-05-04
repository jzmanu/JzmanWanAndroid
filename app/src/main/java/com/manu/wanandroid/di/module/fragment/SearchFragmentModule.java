package com.manu.wanandroid.di.module.fragment;

import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.home.adapter.HomeArticleAdapter;
import com.manu.wanandroid.ui.search.adapter.SearchArticleAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: SearchFragmentModule
 * @Author: jzman
 */
@Module
public class SearchFragmentModule {
    @PreFragment
    @Provides
    SearchArticleAdapter providerSearchArticleAdapter() {
        return new SearchArticleAdapter();
    }
}

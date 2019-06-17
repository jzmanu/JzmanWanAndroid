package com.manu.wanandroid.di.module.fragment;

import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.project.adapter.ProjectArticleAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: ProjectChildFragmentModule
 * @Author: jzman
 * @Date: 2019/5/24 0024 16:15
 */
@Module
public class ProjectChildFragmentModule {
    @PreFragment
    @Provides
    ProjectArticleAdapter providerProjectArticleAdapter(){
        return new ProjectArticleAdapter();
    }
}

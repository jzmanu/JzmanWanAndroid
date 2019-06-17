package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.ProjectChildFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.project.fragment.ProjectChildFragment;

import dagger.Subcomponent;

/**
 * @Desc: ProjectChildFragmentComponent
 * @Author: jzman
 * @Date: 2019/5/24 0024 16:13
 */
@PreFragment
@Subcomponent(modules = ProjectChildFragmentModule.class)
public interface ProjectChildFragmentComponent {
    void injectProjectChildFragment(ProjectChildFragment projectChildFragment);
}

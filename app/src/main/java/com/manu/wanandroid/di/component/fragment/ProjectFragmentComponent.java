package com.manu.wanandroid.di.component.fragment;

import com.manu.wanandroid.di.module.fragment.ProjectFragmentModule;
import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.ui.main.fragment.ProjectFragment;

import dagger.Subcomponent;

/**
 * @Desc: ProjectFragmentComponent
 * @Author: jzman
 * @Date: 2019/5/23 0023 16:54
 */
@PreFragment
@Subcomponent(modules = ProjectFragmentModule.class)
public interface ProjectFragmentComponent {
    void injectProjectFragment(ProjectFragment projectFragment);
}

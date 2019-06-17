package com.manu.wanandroid.di.component.activity;

import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.module.activity.ArticleDetailModule;
import com.manu.wanandroid.di.scope.PreActivity;
import com.manu.wanandroid.ui.home.activity.ArticleDetailActivity;

import dagger.Component;

/**
 * @Desc: ArticleDetailActivityComponent
 * @Author: jzman
 * @Date: 2019/5/17 0017 16:00
 */
@PreActivity
@Component(dependencies = AppComponent.class,modules = ArticleDetailModule.class)
public interface ArticleDetailActivityComponent {
    void injectArticleDetailActivity(ArticleDetailActivity articleDetailActivity);
}

package com.manu.wanandroid.mvp.model;

import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.bean.User;
import com.manu.wanandroid.http.BaseResultBean;
import com.manu.wanandroid.http.HttpHelperImpl;
import com.manu.wanandroid.http.IHttpHelper;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.bean.Banner;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.bean.ProjectTab;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Desc: DataManager
 * @Author: jzman
 * @Date: 2019/5/9 0009 9:45
 */
public class DataManager implements IHttpHelper {
    private IHttpHelper mHttpHelper;

    @Inject
    public DataManager(HttpHelperImpl mHttpHelper) {
        this.mHttpHelper = mHttpHelper;
    }

    @Override
    public Observable<BaseResultBean<User>> login(String username, String password) {
        return mHttpHelper.login(username,password);
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<Article>>> getHomeArticleList(int pageIndex) {
        return mHttpHelper.getHomeArticleList(pageIndex);
    }

    @Override
    public Observable<BaseResultBean<List<Banner>>> getHomeBannerList() {
        return mHttpHelper.getHomeBannerList();
    }

    @Override
    public Observable<BaseResultBean<Object>> collectArticle(String id) {
        return mHttpHelper.collectArticle(id);
    }

    @Override
    public Observable<BaseResultBean<Object>> unCollectArticle(String id) {
        return mHttpHelper.unCollectArticle(id);
    }

    @Override
    public Observable<BaseResultBean<List<ProjectTab>>> getProjectTabList() {
        return mHttpHelper.getProjectTabList();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<Project>>> getProjectList(int pageIndex, int cid) {
        return mHttpHelper.getProjectList(pageIndex, cid);
    }

    @Override
    public Observable<BaseResultBean<List<Knowledge>>> getKsCategoryData() {
        return mHttpHelper.getKsCategoryData();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<Article>>> getKsCategoryArticle(int pageIndex, int cid) {
        return mHttpHelper.getKsCategoryArticle(pageIndex, cid);
    }
}

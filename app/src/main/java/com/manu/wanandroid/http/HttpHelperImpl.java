package com.manu.wanandroid.http;

import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.bean.User;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.http.api.ApiService;
import com.manu.wanandroid.bean.Banner;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.bean.ProjectTab;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Desc: HttpHelperImpl
 * @Author: jzman
 * @Date: 2019/5/8 0008 13:21
 */
public class HttpHelperImpl implements IHttpHelper {
    private final ApiService mApiService;

    @Inject
    public HttpHelperImpl(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseResultBean<User>> login(String username, String password) {
        return mApiService.login(username,password);
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<Article>>> getHomeArticleList(int pageIndex) {
        return mApiService.getHomeArticleList(pageIndex);
    }

    @Override
    public Observable<BaseResultBean<List<Banner>>> getHomeBannerList() {
        return mApiService.getBannerList();
    }

    @Override
    public Observable<BaseResultBean<Object>> collectArticle(String id) {
        return mApiService.collectArticle(id);
    }

    @Override
    public Observable<BaseResultBean<Object>> unCollectArticle(String id) {
        return mApiService.unCollectArticle(id);
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<Collect>>> getCollectArticle(int pageIndex) {
        return mApiService.getCollectArticle(pageIndex);
    }

    @Override
    public Observable<BaseResultBean<List<ProjectTab>>> getProjectTabList() {
        return mApiService.getProjectTabList();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<Project>>> getProjectList(int pageIndex, int cid) {
        return mApiService.getProjectList(pageIndex, cid);
    }

    @Override
    public Observable<BaseResultBean<List<Knowledge>>> getKsCategoryData() {
        return mApiService.getKsCagegoryData();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<Article>>> getKsCategoryArticle(int pageIndex, int cid) {
        return mApiService.getKsCategoryArticle(pageIndex, cid);
    }

}
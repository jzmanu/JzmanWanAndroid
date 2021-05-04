package com.manu.wanandroid.http;

import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.bean.Banner;
import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.bean.HotWord;
import com.manu.wanandroid.bean.Integral;
import com.manu.wanandroid.bean.IntegralInfo;
import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.bean.ProjectTab;
import com.manu.wanandroid.bean.Share;
import com.manu.wanandroid.bean.User;
import com.manu.wanandroid.http.api.ApiService;
import com.manu.wanandroid.http.rx.BasePageBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Desc: HttpHelperImpl
 * @Author: jzman
 */
public class HttpHelperImpl implements IHttpHelper {
    private final ApiService mApiService;

    @Inject
    public HttpHelperImpl(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseResult<User>> login(String username, String password) {
        return mApiService.login(username,password);
    }

    @Override
    public Observable<BaseResult<Object>> logout() {
        return mApiService.logout();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Article>>> getHomeArticleList(int pageIndex) {
        return mApiService.getHomeArticleList(pageIndex);
    }

    @Override
    public Observable<BaseResult<List<Banner>>> getHomeBannerList() {
        return mApiService.getBannerList();
    }

    @Override
    public Observable<BaseResult<Object>> collectArticle(String id) {
        return mApiService.collectArticle(id);
    }

    @Override
    public Observable<BaseResult<Object>> unCollectArticle(String id) {
        return mApiService.unCollectArticle(id);
    }

    @Override
    public Observable<BaseResult<BasePageBean<Collect>>> mineCollectArticle(int pageIndex) {
        return mApiService.mineCollectArticle(pageIndex);
    }

    @Override
    public Observable<BaseResult<List<ProjectTab>>> getProjectTabList() {
        return mApiService.getProjectTabList();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Project>>> getProjectList(int pageIndex, int cid) {
        return mApiService.getProjectList(pageIndex, cid);
    }

    @Override
    public Observable<BaseResult<List<Knowledge>>> getKsCategoryData() {
        return mApiService.getKsCagegoryData();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Article>>> getKsCategoryArticle(int pageIndex, int cid) {
        return mApiService.getKsCategoryArticle(pageIndex, cid);
    }

    @Override
    public Observable<BaseResult<Share<Article>>> mineShareArticle(int pageIndex) {
        return mApiService.mineShareArticle(pageIndex);
    }

    @Override
    public Observable<BaseResult<BasePageBean<Integral>>> mineIntegral(int pageIndex) {
        return mApiService.mineIntegral(pageIndex);
    }

    @Override
    public Observable<BaseResult<IntegralInfo>> mineIntegralInfo() {
        return mApiService.mineIntegralInfo();
    }

    @Override
    public Observable<BaseResult<List<HotWord>>> hotWords() {
        return mApiService.hotWords();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Article>>> search(int pageIndex, String key) {
        return mApiService.search(pageIndex,key);
    }
}
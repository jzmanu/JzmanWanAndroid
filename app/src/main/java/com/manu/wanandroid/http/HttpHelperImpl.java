package com.manu.wanandroid.http;

import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.ui.home.bean.ArticleBean;
import com.manu.wanandroid.http.api.ApiService;
import com.manu.wanandroid.ui.home.bean.BannerBean;
import com.manu.wanandroid.ui.ks.bean.KsBean;
import com.manu.wanandroid.ui.project.bean.ProjectBean;
import com.manu.wanandroid.ui.project.bean.ProjectTabBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Desc: HttpHelperImpl
 * @Author: jzman
 * @Date: 2019/5/8 0008 13:21
 */
public class HttpHelperImpl implements IHttpHelper {
    private ApiService mApiService;

    @Inject
    public HttpHelperImpl(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<ArticleBean>>> getHomeArticleList(int pageIndex) {
        return mApiService.getHomeArticleList(pageIndex);
    }

    @Override
    public Observable<BaseResultBean<List<BannerBean>>> getHomeBannerList() {
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
    public Observable<BaseResultBean<List<ProjectTabBean>>> getProjectTabList() {
        return mApiService.getProjectTabList();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<ProjectBean>>> getProjectList(int pageIndex, int cid) {
        return mApiService.getProjectList(pageIndex, cid);
    }

    @Override
    public Observable<BaseResultBean<List<KsBean>>> getKsCategoryData() {
        return mApiService.getKsCagegoryData();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<ArticleBean>>> getKsCategoryArticle(int pageIndex, int cid) {
        return mApiService.getKsCategoryArticle(pageIndex, cid);
    }

}
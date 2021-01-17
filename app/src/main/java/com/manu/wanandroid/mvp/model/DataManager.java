package com.manu.wanandroid.mvp.model;

import com.manu.wanandroid.http.BaseResultBean;
import com.manu.wanandroid.http.HttpHelperImpl;
import com.manu.wanandroid.http.IHttpHelper;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.bean.ArticleBean;
import com.manu.wanandroid.bean.BannerBean;
import com.manu.wanandroid.bean.KsBean;
import com.manu.wanandroid.bean.ProjectBean;
import com.manu.wanandroid.bean.ProjectTabBean;

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
    public Observable<BaseResultBean<BasePageBean<ArticleBean>>> getHomeArticleList(int pageIndex) {
        return mHttpHelper.getHomeArticleList(pageIndex);
    }

    @Override
    public Observable<BaseResultBean<List<BannerBean>>> getHomeBannerList() {
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
    public Observable<BaseResultBean<List<ProjectTabBean>>> getProjectTabList() {
        return mHttpHelper.getProjectTabList();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<ProjectBean>>> getProjectList(int pageIndex, int cid) {
        return mHttpHelper.getProjectList(pageIndex, cid);
    }

    @Override
    public Observable<BaseResultBean<List<KsBean>>> getKsCategoryData() {
        return mHttpHelper.getKsCategoryData();
    }

    @Override
    public Observable<BaseResultBean<BasePageBean<ArticleBean>>> getKsCategoryArticle(int pageIndex, int cid) {
        return mHttpHelper.getKsCategoryArticle(pageIndex, cid);
    }
}

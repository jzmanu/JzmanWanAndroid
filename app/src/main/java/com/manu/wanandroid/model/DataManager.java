package com.manu.wanandroid.model;

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
import com.manu.wanandroid.http.BaseResult;
import com.manu.wanandroid.http.HttpHelperImpl;
import com.manu.wanandroid.http.IHttpHelper;
import com.manu.wanandroid.http.rx.BasePageBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Desc: DataManager
 * @Author: jzman
 */
public class DataManager implements IHttpHelper {
    private final IHttpHelper mHttpHelper;

    @Inject
    public DataManager(HttpHelperImpl mHttpHelper) {
        this.mHttpHelper = mHttpHelper;
    }

    @Override
    public Observable<BaseResult<User>> login(String username, String password) {
        return mHttpHelper.login(username, password);
    }

    @Override
    public Observable<BaseResult<Object>> logout() {
        return mHttpHelper.logout();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Article>>> getHomeArticleList(int pageIndex) {
        return mHttpHelper.getHomeArticleList(pageIndex);
    }

    @Override
    public Observable<BaseResult<List<Banner>>> getHomeBannerList() {
        return mHttpHelper.getHomeBannerList();
    }

    @Override
    public Observable<BaseResult<Object>> collectArticle(String id) {
        return mHttpHelper.collectArticle(id);
    }

    @Override
    public Observable<BaseResult<Object>> unCollectArticle(String id) {
        return mHttpHelper.unCollectArticle(id);
    }

    @Override
    public Observable<BaseResult<BasePageBean<Collect>>> mineCollectArticle(int pageIndex) {
        return mHttpHelper.mineCollectArticle(pageIndex);
    }

    @Override
    public Observable<BaseResult<List<ProjectTab>>> getProjectTabList() {
        return mHttpHelper.getProjectTabList();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Project>>> getProjectList(int pageIndex, int cid) {
        return mHttpHelper.getProjectList(pageIndex, cid);
    }

    @Override
    public Observable<BaseResult<List<Knowledge>>> getKsCategoryData() {
        return mHttpHelper.getKsCategoryData();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Article>>> getKsCategoryArticle(int pageIndex, int cid) {
        return mHttpHelper.getKsCategoryArticle(pageIndex, cid);
    }

    @Override
    public Observable<BaseResult<Share<Article>>> mineShareArticle(int pageIndex) {
        return mHttpHelper.mineShareArticle(pageIndex);
    }

    @Override
    public Observable<BaseResult<BasePageBean<Integral>>> mineIntegral(int pageIndex) {
        return mHttpHelper.mineIntegral(pageIndex);
    }

    @Override
    public Observable<BaseResult<IntegralInfo>> mineIntegralInfo() {
        return mHttpHelper.mineIntegralInfo();
    }

    @Override
    public Observable<BaseResult<List<HotWord>>> hotWords() {
        return mHttpHelper.hotWords();
    }

    @Override
    public Observable<BaseResult<BasePageBean<Article>>> search(int pageIndex, String key) {
        return mHttpHelper.search(pageIndex, key);
    }
}

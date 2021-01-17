package com.manu.wanandroid.http;

import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.bean.ArticleBean;
import com.manu.wanandroid.bean.BannerBean;
import com.manu.wanandroid.bean.KsBean;
import com.manu.wanandroid.bean.ProjectBean;
import com.manu.wanandroid.bean.ProjectTabBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Desc: IHttpHelper
 * @Author: jzman
 * @Date: 2019/5/8 0008 13:19
 */
public interface IHttpHelper {
    /**
     * 获取首页文章列表
     *
     * @param pageIndex page index
     * @return
     */
    Observable<BaseResultBean<BasePageBean<ArticleBean>>> getHomeArticleList(int pageIndex);

    /**
     * 获取banner列表
     *
     * @return
     */
    Observable<BaseResultBean<List<BannerBean>>> getHomeBannerList();

    /**
     * 收藏文章
     *
     * @return
     */
    Observable<BaseResultBean<Object>> collectArticle(String id);

    /**
     * 取消收藏文章
     *
     * @return
     */
    Observable<BaseResultBean<Object>> unCollectArticle(String id);


    /**
     * 获取项目分类Tab
     *
     * @return
     */
    Observable<BaseResultBean<List<ProjectTabBean>>> getProjectTabList();

    /**
     * 获取项目分类Tab下的文章
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    Observable<BaseResultBean<BasePageBean<ProjectBean>>> getProjectList(int pageIndex, int cid);

    /**
     * 获取知识体系分类
     *
     * @return
     */
    Observable<BaseResultBean<List<KsBean>>> getKsCategoryData();

    /**
     * 获取知识体系分类下面的文章
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    Observable<BaseResultBean<BasePageBean<ArticleBean>>> getKsCategoryArticle(int pageIndex, int cid);
}
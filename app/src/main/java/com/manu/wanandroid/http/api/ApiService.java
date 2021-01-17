package com.manu.wanandroid.http.api;

import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.bean.ArticleBean;
import com.manu.wanandroid.http.BaseResultBean;
import com.manu.wanandroid.bean.BannerBean;
import com.manu.wanandroid.bean.KsBean;
import com.manu.wanandroid.bean.ProjectBean;
import com.manu.wanandroid.bean.ProjectTabBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Desc: ApiService
 * @Author: jzman
 * @Date: 2019/5/8 0008 10:56
 */
public interface ApiService {

    String BASE_URL = "http://www.wanandroid.com/";

    // https://www.wanandroid.com/user/login
    Observable<BaseResultBean<Object>> login();

    /**
     * 获取banner列表
     * https://www.wanandroid.com/banner/json
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseResultBean<List<BannerBean>>> getBannerList();

    /**
     * 获取首页文章列表
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param pageIndex page index
     * @return
     */
    @GET("article/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<ArticleBean>>> getHomeArticleList(@Path("pageIndex") int pageIndex);

    /**
     * 收藏文章
     * https://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResultBean<Object>> collectArticle(@Path("id") String id);

    /**
     * 取消收藏文章
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     *
     * @param id
     * @return
     */

    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResultBean<Object>> unCollectArticle(@Path("id") String id);


    /**
     * 获取项目分类Tab
     * https://www.wanandroid.com/project/tree/json
     *
     * @return
     */

    @GET("project/tree/json")
    Observable<BaseResultBean<List<ProjectTabBean>>> getProjectTabList();

    /**
     * 获取项目分类下的文章
     * https://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    @GET("project/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<ProjectBean>>> getProjectList(@Path("pageIndex") int pageIndex, @Query("cid") int cid);

    /**
     * 获取知识体系分类数据
     * https://www.wanandroid.com/tree/json
     */

    @GET("tree/json")
    Observable<BaseResultBean<List<KsBean>>> getKsCagegoryData();

    /**
     * 获取知识体系下面的文章
     * https://www.wanandroid.com/article/list/0/json?cid=60
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    @GET("article/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<ArticleBean>>> getKsCategoryArticle(@Path("pageIndex") int pageIndex, @Query("cid") int cid);

}



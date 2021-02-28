package com.manu.wanandroid.http.api;

import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.bean.Integral;
import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.bean.User;
import com.manu.wanandroid.bean.Share;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.http.BaseResultBean;
import com.manu.wanandroid.bean.Banner;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.bean.ProjectTab;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    String BASE_URL = "https://www.wanandroid.com/";

    /**
     * 登录
     * https://www.wanandroid.com/user/login
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResultBean<User>> login(@Field("username") String name,@Field("password") String password);

    /**
     * 获取banner列表
     * https://www.wanandroid.com/banner/json
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseResultBean<List<Banner>>> getBannerList();

    /**
     * 获取首页文章列表
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param pageIndex page index
     * @return
     */
    @GET("article/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<Article>>> getHomeArticleList(@Path("pageIndex") int pageIndex);

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
     * 我的收藏
     * https://www.wanandroid.com/lg/collect/list/0/json
     * @param pageIndex 页码
     * @return Observable<BaseResultBean<BasePageBean<Article>>>
     */
    @GET("lg/collect/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<Collect>>> mineCollectArticle(@Path("pageIndex") int pageIndex);


    /**
     * 获取项目分类Tab
     * https://www.wanandroid.com/project/tree/json
     *
     * @return
     */

    @GET("project/tree/json")
    Observable<BaseResultBean<List<ProjectTab>>> getProjectTabList();

    /**
     * 获取项目分类下的文章
     * https://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    @GET("project/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<Project>>> getProjectList(@Path("pageIndex") int pageIndex, @Query("cid") int cid);

    /**
     * 获取知识体系分类数据
     * https://www.wanandroid.com/tree/json
     */

    @GET("tree/json")
    Observable<BaseResultBean<List<Knowledge>>> getKsCagegoryData();

    /**
     * 获取知识体系下面的文章
     * https://www.wanandroid.com/article/list/0/json?cid=60
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    @GET("article/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<Article>>> getKsCategoryArticle(@Path("pageIndex") int pageIndex, @Query("cid") int cid);

    /**
     * 我的分享
     * https://wanandroid.com/user/lg/private_articles/1/json
     * @param pageIndex 页码，从1开始
     * @return Observable<BaseResultBean<BasePageBean<BaseResultBean<List<Article>>>>>
     */
    @GET("user/lg/private_articles/{pageIndex}/json")
    Observable<BaseResultBean<Share<Article>>> mineShareArticle(@Path("pageIndex") int pageIndex);

    /**
     * 我的积分
     * https://www.wanandroid.com//lg/coin/list/1/json
     * @param pageIndex 页码，从1开始
     * @return
     */
    @GET("lg/coin/list/{pageIndex}/json")
    Observable<BaseResultBean<BasePageBean<Integral>>> mineIntegral(@Path("pageIndex") int pageIndex);

}



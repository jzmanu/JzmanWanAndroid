package com.manu.wanandroid.http;

import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.bean.User;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.bean.Banner;
import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.bean.ProjectTab;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Desc: IHttpHelper
 * @Author: jzman
 * @Date: 2019/5/8 0008 13:19
 */
public interface IHttpHelper {
    /**
     * 登录
     * @param username 用户名称
     * @param password 密码
     * @return Observable<BaseResultBean<User>>
     */
    Observable<BaseResultBean<User>> login(String username, String password);
    /**
     * 获取首页文章列表
     *
     * @param pageIndex page index
     * @return
     */
    Observable<BaseResultBean<BasePageBean<Article>>> getHomeArticleList(int pageIndex);

    /**
     * 获取banner列表
     *
     * @return
     */
    Observable<BaseResultBean<List<Banner>>> getHomeBannerList();

    /**
     * 收藏文章
     *  @param id 收藏内容id
     * @return Observable<BaseResultBean<Object>>
     */
    Observable<BaseResultBean<Object>> collectArticle(String id);

    /**
     * 取消收藏文章
     * @param id 收藏内容id
     * @return Observable<BaseResultBean<Object>>
     */
    Observable<BaseResultBean<Object>> unCollectArticle(String id);

    /**
     * 收藏的文章列表
     * @param pageIndex 页面
     * @return  Observable<BaseResultBean<BasePageBean<Collect>>>
     */
    Observable<BaseResultBean<BasePageBean<Collect>>> getCollectArticle(int pageIndex);

    /**
     * 获取项目分类Tab
     *
     * @return
     */
    Observable<BaseResultBean<List<ProjectTab>>> getProjectTabList();

    /**
     * 获取项目分类Tab下的文章
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    Observable<BaseResultBean<BasePageBean<Project>>> getProjectList(int pageIndex, int cid);

    /**
     * 获取知识体系分类
     *
     * @return
     */
    Observable<BaseResultBean<List<Knowledge>>> getKsCategoryData();

    /**
     * 获取知识体系分类下面的文章
     *
     * @param pageIndex
     * @param cid
     * @return
     */
    Observable<BaseResultBean<BasePageBean<Article>>> getKsCategoryArticle(int pageIndex, int cid);
}
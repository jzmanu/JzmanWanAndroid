package com.manu.wanandroid.http;

import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.bean.Integral;
import com.manu.wanandroid.bean.IntegralInfo;
import com.manu.wanandroid.bean.Share;
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
     */
    Observable<BaseResult<User>> login(String username, String password);

    /**
     * 退出登录
     */
    Observable<BaseResult<Object>> logout();
    /**
     * 获取首页文章列表
     * @param pageIndex page index
     */
    Observable<BaseResult<BasePageBean<Article>>> getHomeArticleList(int pageIndex);

    /**
     * 获取banner列表
     */
    Observable<BaseResult<List<Banner>>> getHomeBannerList();

    /**
     * 收藏文章
     *  @param id 收藏内容id
     */
    Observable<BaseResult<Object>> collectArticle(String id);

    /**
     * 取消收藏文章
     * @param id 收藏内容id
     */
    Observable<BaseResult<Object>> unCollectArticle(String id);

    /**
     * 收藏的文章列表
     * @param pageIndex 页面
     */
    Observable<BaseResult<BasePageBean<Collect>>> mineCollectArticle(int pageIndex);

    /**
     * 获取项目分类Tab
     */
    Observable<BaseResult<List<ProjectTab>>> getProjectTabList();

    /**
     * 获取项目分类Tab下的文章
     *
     * @param pageIndex
     * @param cid
     */
    Observable<BaseResult<BasePageBean<Project>>> getProjectList(int pageIndex, int cid);

    /**
     * 获取知识体系分类
     */
    Observable<BaseResult<List<Knowledge>>> getKsCategoryData();

    /**
     * 获取知识体系分类下面的文章
     * @param pageIndex
     * @param cid
     */
    Observable<BaseResult<BasePageBean<Article>>> getKsCategoryArticle(int pageIndex, int cid);

    /**
     * 我的分享
     * @param pageIndex 页码，从1开始
     */
    Observable<BaseResult<Share<Article>>> mineShareArticle(int pageIndex);

    /**
     * 我的积分列表
     * @param pageIndex 页码，从1开始
     */
    Observable<BaseResult<BasePageBean<Integral>>> mineIntegral(int pageIndex);

    /**
     * 我的积分信息
     */
    Observable<BaseResult<IntegralInfo>> mineIntegralInfo();
}
package com.manu.wanandroid.contract.home;

import com.manu.wanandroid.bean.ArticleBean;
import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.mvp.view.IView;
import com.manu.wanandroid.bean.BannerBean;

import java.util.List;

/**
 * @Desc: HomeContract
 * @Author: jzman
 * @Date: 2019/5/8 0008 16:55
 */
public interface HomeContract {
    interface View extends IView {

        void onHomeArticleListSuccess(List<ArticleBean> result);

        void onHomeBannerListSuccess(List<BannerBean> result);
    }

    interface Presenter extends IPresenter<View> {
        void getHomeBannerList();

        void getHomeArticleList(int pageIndex);
    }
}

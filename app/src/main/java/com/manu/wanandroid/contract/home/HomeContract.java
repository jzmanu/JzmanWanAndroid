package com.manu.wanandroid.contract.home;

import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.base.mvp.presenter.IPresenter;
import com.manu.wanandroid.base.mvp.view.IView;
import com.manu.wanandroid.bean.Banner;

import java.util.List;

/**
 * @Desc: HomeContract
 * @Author: jzman
 * @Date: 2019/5/8 0008 16:55
 */
public interface HomeContract {
    interface View extends IView {

        void onHomeArticleListSuccess(List<Article> result);

        void onHomeBannerListSuccess(List<Banner> result);
    }

    interface Presenter extends IPresenter<View> {
        void getHomeBannerList();

        void getHomeArticleList(int pageIndex);
    }
}

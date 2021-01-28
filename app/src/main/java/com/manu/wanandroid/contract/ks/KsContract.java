package com.manu.wanandroid.contract.ks;

import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.mvp.view.IView;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.bean.Knowledge;

import java.util.List;

/**
 * @Desc: KsContract
 * @Author: jzman
 * @Date: 2019/5/31 0031 11:12
 */
public interface KsContract {
    interface CategoryView extends IView {
        void onKsCategoryDataSuccess(List<Knowledge> result);
    }

    interface CategoryArticleView extends IView {
        void onKsCategoryArticleSuccess(List<Article> result);
    }

    interface CategoryPresenter extends IPresenter<CategoryView> {
        void getKsCategoryData();
    }

    interface CategoryArticlePresenter extends IPresenter<CategoryArticleView> {
        void getKsCategoryArticleList(int pageIndex, int cid);
    }
}

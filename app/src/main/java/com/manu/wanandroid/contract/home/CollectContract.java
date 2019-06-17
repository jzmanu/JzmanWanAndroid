package com.manu.wanandroid.contract.home;

import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.mvp.view.IView;

/**
 * @Desc: CollectContract
 * @Author: jzman
 * @Date: 2019/5/16 0016 15:53
 */
public interface CollectContract {
    interface View extends IView {

        void onCollectArticleSuccess();

        void onUnCollectArticleSuccess();
    }

    interface Presenter extends IPresenter<CollectContract.View> {
        void collectArticle(String id);

        void unCollectArticle(String id);
    }
}

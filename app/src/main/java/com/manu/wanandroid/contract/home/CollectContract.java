package com.manu.wanandroid.contract.home;

import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.mvp.view.IView;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * @Desc: CollectContract
 * @Author: jzman
 * @Date: 2019/5/16
 */
public interface CollectContract {
    interface View extends IView {

        void onCollectArticleSuccess();

        void onUnCollectArticleSuccess();

        void onGetCollectArticleSuccess(@NonNull List<Collect> result);
    }

    interface Presenter extends IPresenter<CollectContract.View> {
        void collectArticle(String id);

        void unCollectArticle(String id);

        void getCollectArticle(int pageIndex);
    }
}

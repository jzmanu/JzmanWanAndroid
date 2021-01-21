package com.manu.wanandroid.presenter.home;

import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.mvp.model.DataManager;
import com.manu.wanandroid.contract.home.HomeContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.mvp.presenter.BasePresenter;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.bean.Banner;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

/**
 * @Desc: HomePresenter
 * @Author: jzman
 * @Date: 2019/5/8 0008 17:07
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getHomeBannerList() {
        addRxSubscribe(mDataManager.getHomeBannerList()
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<List<Banner>>(mView) {
                    @Override
                    public void onNext(@NotNull List<Banner> bannerBeans) {
                        mView.onHomeBannerListSuccess(bannerBeans);
                    }
                })
        );
    }

    @Override
    public void getHomeArticleList(int pageIndex) {
        addRxSubscribe(mDataManager.getHomeArticleList(pageIndex)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<BasePageBean<Article>>(mView) {
                    @Override
                    public void onNext(@NotNull BasePageBean<Article> result) {
                        mView.onHomeArticleListSuccess(result.getDatas());
                    }
                })
        );
    }
}

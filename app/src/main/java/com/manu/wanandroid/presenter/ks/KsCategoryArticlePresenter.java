package com.manu.wanandroid.presenter.ks;

import com.manu.wanandroid.contract.ks.KsContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.model.DataManager;
import com.manu.wanandroid.base.mvp.presenter.BasePresenter;
import com.manu.wanandroid.bean.Article;

import javax.inject.Inject;

/**
 * @Desc: KsCategoryArticlePresenter
 * @Author: jzman
 * @Date: 2019/5/31 0031 13:20
 */
public class KsCategoryArticlePresenter extends BasePresenter<KsContract.CategoryArticleView>
        implements KsContract.CategoryArticlePresenter {

    @Inject
    public KsCategoryArticlePresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getKsCategoryArticleList(int pageIndex, int cid) {
        addRxSubscribe(mDataManager.getKsCategoryArticle(pageIndex, cid)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<BasePageBean<Article>>(mView) {
                    @Override
                    public void onNext(BasePageBean<Article> beanList) {
                        mView.onKsCategoryArticleSuccess(beanList.getDatas());
                    }
                }));
    }
}

package com.manu.wanandroid.presenter.search;

import com.manu.wanandroid.base.mvp.presenter.BasePresenter;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.bean.HotWord;
import com.manu.wanandroid.contract.search.SearchContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.model.DataManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * SearchPresenter
 * @Desc: SearchPresenter
 * @Author: jzman
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getSimpleName();

    @Inject
    public SearchPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void hotWords() {
        addRxSubscribe(mDataManager.hotWords()
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<List<HotWord>>(mView) {
                    @Override
                    public void onNext(@NonNull List<HotWord> result) {
                        mView.onHotWordsSuccess(result);
                    }
                })
        );
    }

    @Override
    public void search(int pageIndex, String key) {
        addRxSubscribe(mDataManager.search(pageIndex,key)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<BasePageBean<Article>>(mView) {
                    @Override
                    public void onNext(@NotNull BasePageBean<Article> result) {
                        mView.onSearchSuccess(result.getDatas());
                    }
                })
        );
    }
}

package com.manu.wanandroid.presenter.ks;

import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.contract.ks.KsContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.mvp.model.DataManager;
import com.manu.wanandroid.mvp.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @Desc: KsCategoryDataPresenter
 * @Author: jzman
 * @Date: 2019/5/31 0031 11:29
 */
public class KsCategoryDataPresenter extends BasePresenter<KsContract.CategoryView> implements KsContract.CategoryPresenter {
    private static final String TAG = KsCategoryDataPresenter.class.getSimpleName();

    @Inject
    public KsCategoryDataPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getKsCategoryData() {
        addRxSubscribe(mDataManager.getKsCategoryData()
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<List<Knowledge>>(mView) {
                    @Override
                    public void onNext(List<Knowledge> ksBeans) {
                        mView.onKsCategoryDataSuccess(ksBeans);
                    }
                }));
    }
}

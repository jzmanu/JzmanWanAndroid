package com.manu.wanandroid.presenter.project;

import com.manu.wanandroid.contract.project.ProjectContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.mvp.model.DataManager;
import com.manu.wanandroid.mvp.presenter.BasePresenter;
import com.manu.wanandroid.bean.ProjectTabBean;

import java.util.List;

import javax.inject.Inject;

/**
 * @Desc: ProjectTabPresenter
 * @Author: jzman
 * @Date: 2019/5/24 0024 11:23
 */
public class ProjectTabPresenter extends BasePresenter<ProjectContract.TabView> implements ProjectContract.TabPresenter {
    @Inject
    public ProjectTabPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getProjectTabList() {
        addRxSubscribe(mDataManager.getProjectTabList()
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<List<ProjectTabBean>>(mView) {
                    @Override
                    public void onNext(List<ProjectTabBean> projectTabBeans) {
                        mView.onProjectTabSuccess(projectTabBeans);
                    }
                }));
    }

}

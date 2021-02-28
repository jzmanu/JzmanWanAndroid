package com.manu.wanandroid.presenter.project;

import com.manu.wanandroid.contract.project.ProjectContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.model.DataManager;
import com.manu.wanandroid.base.mvp.presenter.BasePresenter;
import com.manu.wanandroid.bean.ProjectTab;

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
                .subscribeWith(new BaseObserver<List<ProjectTab>>(mView) {
                    @Override
                    public void onNext(List<ProjectTab> projectTabBeans) {
                        mView.onProjectTabSuccess(projectTabBeans);
                    }
                }));
    }

}

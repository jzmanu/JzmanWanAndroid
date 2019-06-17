package com.manu.wanandroid.presenter.project;

import android.util.Log;

import com.manu.wanandroid.contract.project.ProjectContract;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.BasePageBean;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.mvp.model.DataManager;
import com.manu.wanandroid.mvp.presenter.BasePresenter;
import com.manu.wanandroid.ui.project.bean.ProjectBean;
import com.manu.wanandroid.utils.L;

import javax.inject.Inject;

/**
 * @Desc: ProjectPresenter
 * @Author: jzman
 * @Date: 2019/5/23 0023 16:19
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.ProjectView> implements ProjectContract.ProjectPresenter {

    private static final String TAG = ProjectPresenter.class.getSimpleName();

    @Inject
    public ProjectPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void getProjectList(int pageIndex, int cid) {
        L.i(TAG, cid + "");
        addRxSubscribe(mDataManager.getProjectList(pageIndex, cid)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<BasePageBean<ProjectBean>>(mView) {
                    @Override
                    public void onNext(BasePageBean<ProjectBean> projectTabBeans) {
                        mView.onProjectSuccess(projectTabBeans.getDatas());
                    }
                }));
    }
}
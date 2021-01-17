package com.manu.wanandroid.contract.project;

import com.manu.wanandroid.mvp.presenter.IPresenter;
import com.manu.wanandroid.mvp.view.IView;
import com.manu.wanandroid.bean.ProjectBean;
import com.manu.wanandroid.bean.ProjectTabBean;

import java.util.List;

/**
 * @Desc: ProjectContract
 * @Author: jzman
 * @Date: 2019/5/23 0023 16:20
 */
public interface ProjectContract {
    interface TabView extends IView {
        void onProjectTabSuccess(List<ProjectTabBean> result);
    }

    interface TabPresenter extends IPresenter<TabView> {
        void getProjectTabList();
    }

    interface ProjectView extends IView {
        void onProjectSuccess(List<ProjectBean> result);
    }

    interface ProjectPresenter extends IPresenter<ProjectView> {
        void getProjectList(int pageIndex, int cid);
    }

}

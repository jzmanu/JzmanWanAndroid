package com.manu.wanandroid.ui.project.fragment;

import com.google.android.material.tabs.TabLayout;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.contract.project.ProjectContract;
import com.manu.wanandroid.di.module.fragment.ProjectFragmentModule;
import com.manu.wanandroid.presenter.project.ProjectTabPresenter;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.ui.project.adapter.ProjectChildFragmentPageAdapter;
import com.manu.wanandroid.bean.ProjectTabBean;
import com.manu.wanandroid.utils.L;

import java.util.List;

import javax.inject.Inject;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @Desc: ProjectFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:55
 */
public class ProjectFragment extends BaseLoadMvpFragment<ProjectContract.TabPresenter> implements ProjectContract.TabView,
        TabLayout.OnTabSelectedListener {
    private static final String TAG = ProjectFragment.class.getSimpleName();
    @BindView(R.id.tl_project)
    TabLayout tlProject;
    @BindView(R.id.vp_project)
    ViewPager vpProject;

    @Inject
    ProjectTabPresenter mProjectTabPresenter;

    @Override
    public int onLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void onInject() {
        MainActivity mActivity = (MainActivity) getActivity();
        assert mActivity != null;
        mActivity.mMainActivityComponent
                .getProjectFragmentComponent(new ProjectFragmentModule())
                .injectProjectFragment(this);
    }

    @Override
    protected ProjectContract.TabPresenter onPresenter() {
        return mProjectTabPresenter;
    }

    @Override
    public void onInitData() {
        L.i(TAG, "onInitData");
        mProjectTabPresenter.getProjectTabList();
    }

    @Override
    public void onLazyLoad() {
        L.i(TAG, "onLazyLoad");

    }

    @Override
    public void onProjectTabSuccess(List<ProjectTabBean> result) {
        L.i(TAG, "onProjectTabSuccess" + result);
        onShowNormalContent();
        if (result != null && result.size() > 0) {
            for (ProjectTabBean tabBean : result) {
                tlProject.addTab(tlProject.newTab().setText(tabBean.getName()));
            }
            tlProject.setTabMode(TabLayout.MODE_SCROLLABLE);
            tlProject.addOnTabSelectedListener(this);
            ProjectChildFragmentPageAdapter mPageAdapter = new ProjectChildFragmentPageAdapter(getChildFragmentManager(), result);
            vpProject.setAdapter(mPageAdapter);
            tlProject.setupWithViewPager(vpProject);
            vpProject.setOffscreenPageLimit(2);
        }else{
            onShowEmptyMessage();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

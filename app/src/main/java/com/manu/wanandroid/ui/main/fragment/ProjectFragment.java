package com.manu.wanandroid.ui.main.fragment;

import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.bean.ProjectTab;
import com.manu.wanandroid.contract.project.ProjectContract;
import com.manu.wanandroid.databinding.FragmentProjectBinding;
import com.manu.wanandroid.di.module.fragment.ProjectFragmentModule;
import com.manu.wanandroid.presenter.project.ProjectTabPresenter;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.ui.project.adapter.ProjectChildFragmentPageAdapter;
import com.manu.wanandroid.utils.L;

import java.util.List;

import javax.inject.Inject;

/**
 * @Desc: ProjectFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:55
 */
public class ProjectFragment extends BaseLoadMvpFragment<ProjectContract.TabPresenter> implements ProjectContract.TabView,
        TabLayout.OnTabSelectedListener {
    private static final String TAG = ProjectFragment.class.getSimpleName();

    private FragmentProjectBinding binding;

    @Inject
    ProjectTabPresenter mProjectTabPresenter;

    @Override
    public View onLayout() {
        binding = FragmentProjectBinding.inflate(getLayoutInflater());
        return binding.getRoot();
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
    public void onData() {
        L.i(TAG, "onInitData");
        mProjectTabPresenter.getProjectTabList();
    }

    @Override
    public void onLazyLoad() {
        L.i(TAG, "onLazyLoad");

    }

    @Override
    public void onProjectTabSuccess(List<ProjectTab> result) {
        L.i(TAG, "onProjectTabSuccess" + result);
        onShowNormalContent();
        if (result != null && result.size() > 0) {
            binding.tlProject.setTabMode(TabLayout.MODE_SCROLLABLE);
            binding.tlProject.addOnTabSelectedListener(this);
            ProjectChildFragmentPageAdapter mPageAdapter = new ProjectChildFragmentPageAdapter(getChildFragmentManager(), result);
            binding.vpProject.setAdapter(mPageAdapter);
            binding.tlProject.setupWithViewPager(binding.vpProject);
            binding.vpProject.setOffscreenPageLimit(2);
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

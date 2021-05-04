package com.manu.wanandroid.ui.project.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.common.Config;
import com.manu.wanandroid.contract.project.ProjectContract;
import com.manu.wanandroid.databinding.FragmentProjectChildBinding;
import com.manu.wanandroid.di.module.fragment.ProjectChildFragmentModule;
import com.manu.wanandroid.presenter.project.ProjectPresenter;
import com.manu.wanandroid.ui.home.activity.ArticleDetailActivity;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.ui.project.adapter.ProjectArticleAdapter;
import com.manu.wanandroid.utils.L;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Desc: ProjectChildFragment
 * @Author: jzman
 * @Date: 2019/5/23 0023 17:32
 */
public class ProjectChildFragment extends BaseLoadMvpFragment<ProjectContract.ProjectPresenter> implements ProjectContract.ProjectView,
        OnRefreshListener, OnLoadMoreListener {
    private static final String TAG = ProjectChildFragment.class.getSimpleName();
    private static final String ARG_PARAM_TAB_ID = "arg_param_tab_id";

    private FragmentProjectChildBinding binding;

    @Inject
    ProjectPresenter mProjectPresenter;
    @Inject
    ProjectArticleAdapter mProjectArticleAdapter;

    private int mPageIndex;
    private MainActivity mActivity;
    private int mCid;
    private SkeletonScreen mSkeletonScreen;

    @Override
    public View onLayout() {
        binding = FragmentProjectChildBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onInject() {
        mActivity = (MainActivity) getActivity();
        assert mActivity != null;
        mActivity.mMainActivityComponent
                .getProjectChildFragmentComponent(new ProjectChildFragmentModule())
                .injectProjectChildFragment(this);
    }

    @Override
    public void onData() {
        Bundle bundle = getArguments();
        assert bundle != null;
        mCid = bundle.getInt(ARG_PARAM_TAB_ID);
        binding.normalView.setOnRefreshListener(this);
        binding.normalView.setOnLoadMoreListener(this);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.drawable.rv_divider_bg);
        assert drawable != null;
        itemDecoration.setDrawable(drawable);
        binding.rvProject.addItemDecoration(itemDecoration);

        mSkeletonScreen = Skeleton.bind(binding.rvProject)
                .adapter(mProjectArticleAdapter)
                .load(R.layout.recycle_project_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(Config.skeletonDuration)
                .show();

        binding.rvProject.addOnItemTouchListener(new OnRecycleItemClickListener(binding.rvProject) {
            @Override
            public void onRecycleItemClick(View view, int position, RecyclerView.ViewHolder holder) {
                Project bean = mProjectArticleAdapter.getItem(holder.getAdapterPosition());
                ArticleDetailActivity.startArticleDetailActivityForResult(
                        mActivity, bean.getId(), bean.getLink(), bean.isCollect(),
                        result -> {
                            Intent intent = result.getData();
                            if (intent == null) return;
                            boolean isRefresh = intent.getBooleanExtra(
                                    ArticleDetailActivity.ARTICLE_REFRESH, false);
                            if (result.getResultCode() == Activity.RESULT_OK && isRefresh) {
                                binding.normalView.autoRefresh();
                            }
                        });
            }
        });
        binding.normalView.autoRefresh();
    }

    @Override
    protected ProjectContract.ProjectPresenter onPresenter() {
        return mProjectPresenter;
    }

    public static ProjectChildFragment newInstance(int tabId) {
        ProjectChildFragment fragment = new ProjectChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_TAB_ID, tabId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onProjectSuccess(List<Project> result) {
        L.i(TAG, "onProjectSuccess");
        onShowNormalContent();
        if (mPageIndex == 1) {
            mSkeletonScreen.hide();
            mProjectArticleAdapter.clear();
            if (result.size() == 0) {
                onShowEmptyMessage();
                binding.normalView.setEnableLoadMore(false);
            }
        }

        mProjectArticleAdapter.addAll(result);
        if (result.size() == 0) binding.normalView.setEnableLoadMore(false);
        binding.normalView.finishRefresh();
        binding.normalView.finishLoadMore();
    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        mSkeletonScreen.hide();
        binding.normalView.finishRefresh();
        binding.normalView.finishLoadMore();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        mProjectPresenter.getProjectList(mPageIndex, mCid);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex = 1;
        mProjectPresenter.getProjectList(mPageIndex, mCid);
    }
}

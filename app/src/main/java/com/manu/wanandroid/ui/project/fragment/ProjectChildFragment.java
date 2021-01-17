package com.manu.wanandroid.ui.project.fragment;

import android.os.Bundle;
import android.view.View;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.contract.project.ProjectContract;
import com.manu.wanandroid.di.module.fragment.ProjectChildFragmentModule;
import com.manu.wanandroid.presenter.project.ProjectPresenter;
import com.manu.wanandroid.ui.home.activity.ArticleDetailActivity;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.ui.project.adapter.ProjectArticleAdapter;
import com.manu.wanandroid.bean.ProjectBean;
import com.manu.wanandroid.utils.L;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @Desc: ProjectChildFragment
 * @Author: jzman
 * @Date: 2019/5/23 0023 17:32
 */
public class ProjectChildFragment extends BaseLoadMvpFragment<ProjectContract.ProjectPresenter> implements ProjectContract.ProjectView,
        OnRefreshListener, OnLoadMoreListener {
    private static final String TAG = ProjectChildFragment.class.getSimpleName();
    private static final String ARG_PARAM_TAB_ID = "arg_param_tab_id";

    @BindView(R.id.rv_project)
    RecyclerView rv_project;
    @BindView(R.id.normal_view)
    SmartRefreshLayout srlProject;

    @Inject
    ProjectPresenter mProjectPresenter;
    @Inject
    ProjectArticleAdapter mProjectArticleAdapter;

    private int mPageIndex;
    private MainActivity mActivity;
    private int mCid;
    private SkeletonScreen mSkeletonScreen;

    @Override
    public int onLayoutId() {
        return R.layout.fragment_project_child;
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
    public void onInitData() {
        Bundle bundle = getArguments();
        assert bundle != null;
        mCid = bundle.getInt(ARG_PARAM_TAB_ID);
        rv_project.setLayoutManager(new LinearLayoutManager(mActivity));
        srlProject.setOnRefreshListener(this);
        srlProject.setOnLoadMoreListener(this);

        mSkeletonScreen = Skeleton.bind(rv_project)
                .adapter(mProjectArticleAdapter)
                .load(R.layout.recycle_project_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(1500)
                .show();

        rv_project.addOnItemTouchListener(new OnRecycleItemClickListener(rv_project) {
            @Override
            public void onRecycleItemClick(View view, int position, RecyclerView.ViewHolder holder) {
                ProjectBean bean = mProjectArticleAdapter.getItem(holder.getAdapterPosition());
                ArticleDetailActivity.startArticleDetailActivity(mActivity, bean.getId(), bean.getLink(), bean.isCollect());
            }
        });
        srlProject.autoRefresh();
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
    public void onProjectSuccess(List<ProjectBean> result) {
        L.i(TAG, "onProjectSuccess");
        onShowNormalContent();
        if (mPageIndex == 0) {
            mSkeletonScreen.hide();
            mProjectArticleAdapter.clear();
            if (result.size() == 0) {
                onShowEmptyMessage();
                srlProject.setEnableLoadMore(false);
            }
        }

        mProjectArticleAdapter.addAll(result);
        if (result.size() == 0) srlProject.setEnableLoadMore(false);

        srlProject.finishRefresh();
        srlProject.finishLoadMore();
    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        mSkeletonScreen.hide();
        srlProject.finishRefresh();
        srlProject.finishLoadMore();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        mProjectPresenter.getProjectList(mPageIndex, mCid);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex = 0;
        mProjectPresenter.getProjectList(mPageIndex, mCid);
    }
}

package com.manu.wanandroid.ui.ks.fragment;

import android.os.Bundle;
import android.view.View;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.contract.ks.KsContract;
import com.manu.wanandroid.di.module.fragment.KsRightChildFragmentModule;
import com.manu.wanandroid.presenter.ks.KsCategoryArticlePresenter;
import com.manu.wanandroid.ui.home.activity.ArticleDetailActivity;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.ui.ks.adapter.KsCategoryArticleAdapter;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.L;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @Desc: KsRightChildFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:55
 */
public class KsRightChildFragment extends BaseLoadMvpFragment<KsContract.CategoryArticlePresenter> implements KsContract.CategoryArticleView,
        OnRefreshLoadMoreListener {

    private static final String TAG = KsRightChildFragment.class.getSimpleName();
    private static final String ARG_PARAM_TAB_ID = "arg_param_tab_id";

    @BindView(R.id.rv_ks_right)
    RecyclerView rvKsRight;
    @BindView(R.id.normal_view)
    SmartRefreshLayout srlKsRight;

    @Inject
    KsCategoryArticleAdapter mKsCategoryArticleAdapter;
    @Inject
    KsCategoryArticlePresenter mKsCategoryArticlePresenter;

    private MainActivity mActivity;
    private int mPageIndex;
    private int mId;
    private SkeletonScreen mSkeletonScreen;

    @Override
    public int onLayoutId() {
        return R.layout.fragment_ks_article_right;
    }

    @Override
    public void onInject() {
        mActivity = (MainActivity) getActivity();
        assert mActivity != null;
        mActivity.mMainActivityComponent
                .getKsRightChildFragment(new KsRightChildFragmentModule())
                .injectKsRightArticleFragment(this);
    }

    @Override
    protected KsContract.CategoryArticlePresenter onPresenter() {
        return mKsCategoryArticlePresenter;
    }

    public static KsRightChildFragment newInstance(int tabId) {
        KsRightChildFragment fragment = new KsRightChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_TAB_ID, tabId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onInitData() {
        L.i(TAG, "onInitData");

        Bundle bundle = getArguments();
        assert bundle != null;
        mId = bundle.getInt(ARG_PARAM_TAB_ID);

        srlKsRight.setOnRefreshListener(this);
        srlKsRight.setOnLoadMoreListener(this);
        rvKsRight.setLayoutManager(new LinearLayoutManager(mActivity));

        mSkeletonScreen = Skeleton.bind(rvKsRight)
                .adapter(mKsCategoryArticleAdapter)
                .load(R.layout.recycle_home_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(1500)
                .show();

        rvKsRight.addOnItemTouchListener(new OnRecycleItemClickListener(rvKsRight) {
            @Override
            public void onRecycleItemClick(View view, int position, RecyclerView.ViewHolder holder) {
                Article bean = mKsCategoryArticleAdapter.getItem(holder.getAdapterPosition());
                ArticleDetailActivity.startArticleDetailActivity(mActivity, bean.getId(), bean.getLink(), bean.isCollect());
            }
        });

        srlKsRight.autoRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex = 0;
        mKsCategoryArticlePresenter.getKsCategoryArticleList(mPageIndex, mId);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        mKsCategoryArticlePresenter.getKsCategoryArticleList(mPageIndex, mId);
    }

    @Override
    public void onKsCategoryArticleSuccess(List<Article> result) {
        L.i(TAG, "onKsCategoryArticleSuccess");
        onShowNormalContent();
        if (mPageIndex == 0) {
            mSkeletonScreen.hide();
            mKsCategoryArticleAdapter.clear();
            if (result.size() == 0) {
                onShowEmptyMessage();
                srlKsRight.setEnableLoadMore(false);
            }
        }

        mKsCategoryArticleAdapter.addAll(result);
        if (result.size() == 0) srlKsRight.setEnableLoadMore(false);

        srlKsRight.finishRefresh();
        srlKsRight.finishLoadMore();
    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        mSkeletonScreen.hide();
        srlKsRight.finishRefresh();
        srlKsRight.finishLoadMore();
    }
}
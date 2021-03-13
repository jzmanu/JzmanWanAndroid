package com.manu.wanandroid.ui.ks.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.common.Config;
import com.manu.wanandroid.contract.ks.KsContract;
import com.manu.wanandroid.databinding.FragmentKsArticleRightBinding;
import com.manu.wanandroid.di.module.fragment.KsRightChildFragmentModule;
import com.manu.wanandroid.presenter.ks.KsCategoryArticlePresenter;
import com.manu.wanandroid.ui.home.activity.ArticleDetailActivity;
import com.manu.wanandroid.ui.ks.adapter.KsCategoryArticleAdapter;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.L;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Desc: KsRightChildFragment
 * @Author: jzman
 */
public class KsRightArticleFragment extends BaseLoadMvpFragment<KsContract.CategoryArticlePresenter> implements KsContract.CategoryArticleView,
        OnRefreshLoadMoreListener {

    private static final String TAG = KsRightArticleFragment.class.getSimpleName();
    private static final String ARG_PARAM_TAB_ID = "arg_param_tab_id";

    private FragmentKsArticleRightBinding binding;

    @Inject
    KsCategoryArticleAdapter mKsCategoryArticleAdapter;
    @Inject
    KsCategoryArticlePresenter mKsCategoryArticlePresenter;

    private MainActivity mActivity;
    private int mPageIndex;
    private int mId;
    private SkeletonScreen mSkeletonScreen;

    @Override
    public View onLayout() {
        binding = FragmentKsArticleRightBinding.inflate(getLayoutInflater());
        return binding.getRoot();
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

    public static KsRightArticleFragment newInstance(int tabId) {
        KsRightArticleFragment fragment = new KsRightArticleFragment();
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

        binding.normalView.setOnRefreshListener(this);
        binding.normalView.setOnLoadMoreListener(this);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.drawable.rv_divider_bg);
        assert drawable != null;
        itemDecoration.setDrawable(drawable);
        binding.rvKsRight.addItemDecoration(itemDecoration);

        mSkeletonScreen = Skeleton.bind(binding.rvKsRight)
                .adapter(mKsCategoryArticleAdapter)
                .load(R.layout.recycle_home_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(Config.INSTANCE.getSkeletonDuration())
                .show();

        binding.rvKsRight.addOnItemTouchListener(new OnRecycleItemClickListener(binding.rvKsRight) {
            @Override
            public void onRecycleItemClick(View view, int position, RecyclerView.ViewHolder holder) {
                Article bean = mKsCategoryArticleAdapter.getItem(holder.getAdapterPosition());
                ArticleDetailActivity.startArticleDetailActivityForResult(
                        mActivity, bean.getId(), bean.getLink(), bean.isCollect(),
                        result -> {
                            boolean isRefresh = result.getData().getBooleanExtra(
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
                binding.normalView.setEnableLoadMore(false);
            }
        }

        mKsCategoryArticleAdapter.addAll(result);
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
}
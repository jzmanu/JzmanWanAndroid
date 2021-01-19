package com.manu.wanandroid.ui.home.fragment;

import android.view.View;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.contract.home.HomeContract;
import com.manu.wanandroid.di.module.fragment.HomeFragmentModule;
import com.manu.wanandroid.presenter.home.HomePresenter;
import com.manu.wanandroid.ui.home.activity.ArticleDetailActivity;
import com.manu.wanandroid.ui.home.adapter.HomeArticleAdapter;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.bean.Banner;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.GlideImageLoader;
import com.manu.wanandroid.utils.L;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @Desc: HomeFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:55
 */
public class HomeFragment extends BaseLoadMvpFragment<HomeContract.Presenter> implements HomeContract.View,
        OnRefreshLoadMoreListener, OnLoadMoreListener, HomeArticleAdapter.OnBannerListener {
    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.normal_view)
    SmartRefreshLayout srlHome;
    @Inject
    HomeArticleAdapter mHomeArticleAdapter;
    @Inject
    HomePresenter mHomePresenter;

    private MainActivity mActivity;
    private com.youth.banner.Banner mHomeBanner;
    private int mPageIndex;
    private SkeletonScreen mSkeletonScreen;
    private List<String> mBannerImages;

    @Override
    public int onLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onInject() {
        mActivity = (MainActivity) getActivity();
        assert mActivity != null;
        mActivity.mMainActivityComponent
                .getHomeFragmentComponent(new HomeFragmentModule())
                .injectHomeFragment(this);
    }

    @Override
    protected HomeContract.Presenter onPresenter() {
        return mHomePresenter;
    }

    @Override
    public void onInitData() {
        L.i(TAG, "onInitData");
        srlHome.setOnRefreshListener(this);
        srlHome.setOnLoadMoreListener(this);
        rvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        mHomeArticleAdapter.setOnBannerListener(this);

        mSkeletonScreen = Skeleton.bind(rvHome)
                .adapter(mHomeArticleAdapter)
                .load(R.layout.recycle_home_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(1500)
                .show();

        rvHome.addOnItemTouchListener(new OnRecycleItemClickListener(rvHome) {
            @Override
            public void onRecycleItemClick(View view, int position, RecyclerView.ViewHolder holder) {
                Article bean = mHomeArticleAdapter.getItem(holder.getAdapterPosition()-1);
                ArticleDetailActivity.startArticleDetailActivity(mActivity, bean.getId(), bean.getLink(), bean.isCollect());
            }
        });

        srlHome.autoRefresh();
    }

    @Override
    public void onLazyLoad() {
        L.i(TAG, "onLazyLoad");

    }

    @Override
    public void onHomeArticleListSuccess(List<Article> result) {
        L.i(TAG, "onHomeArticleListSuccess" + result);
        onShowNormalContent();
        if (mPageIndex == 0) {
            mSkeletonScreen.hide();
            mHomeArticleAdapter.clear();
            if (result.size() == 0) {
                onShowEmptyMessage();
                srlHome.setEnableLoadMore(false);
            }
        }

        mHomeArticleAdapter.addAll(result);
        if (result.size() == 0) srlHome.setEnableLoadMore(false);

        srlHome.finishRefresh();
        srlHome.finishLoadMore();
    }

    @Override
    public void onHomeBannerListSuccess(List<Banner> result) {
        L.i(TAG, "onHomeBannerListSuccess" + result);
        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                Banner bean = result.get(i);
                mBannerImages.add(bean.getImagePath());
            }
            setBannerSource();
        }
    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        mSkeletonScreen.hide();
        srlHome.finishRefresh();
        srlHome.finishLoadMore();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mHomeBanner != null) mHomeBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mHomeBanner != null) mHomeBanner.stopAutoPlay();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex = 0;
        mHomePresenter.getHomeArticleList(mPageIndex);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        mHomePresenter.getHomeArticleList(mPageIndex);
    }

    @Override
    public void onBannerInit(com.youth.banner.Banner banner) {
        mHomeBanner = banner;
        mBannerImages = new ArrayList<>();
        if (mBannerImages.size() > 0){
            setBannerSource();
        }else{
            mHomePresenter.getHomeBannerList();
        }
    }

    public void setBannerSource() {
        if (mHomeBanner != null) {
            mHomeBanner.setImageLoader(new GlideImageLoader());
            mHomeBanner.setImages(mBannerImages);
            mHomeBanner.start();
        }
    }
}

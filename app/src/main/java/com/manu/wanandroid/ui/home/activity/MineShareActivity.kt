package com.manu.wanandroid.ui.home.activity

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.manu.wanandroid.R
import com.manu.wanandroid.app.App
import com.manu.wanandroid.base.activity.BaseLoadMvpActivity
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener
import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.common.Config
import com.manu.wanandroid.contract.home.ShareContract
import com.manu.wanandroid.databinding.ActivityMineShareBinding
import com.manu.wanandroid.di.component.activity.DaggerMineShareActivityComponent
import com.manu.wanandroid.presenter.home.MineSharePresenter
import com.manu.wanandroid.ui.home.adapter.MineShareAdapter
import com.manu.wanandroid.utils.L
import com.manu.wanandroid.utils.StatusBarUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import javax.inject.Inject

/**
 * @Desc: 我的分享
 * @Author: jzman
 */
class MineShareActivity : BaseLoadMvpActivity<ShareContract.Presenter>(), ShareContract.View,
        OnRefreshLoadMoreListener, OnLoadMoreListener {
    @Inject
    lateinit var mCollectArticleAdapter: MineShareAdapter

    @Inject
    lateinit var mCollPresenter: MineSharePresenter

    private lateinit var binding: ActivityMineShareBinding
    private lateinit var mSkeletonScreen: SkeletonScreen
    private var mPageIndex = 0

    override fun onLayout(): View {
        binding = ActivityMineShareBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {
        val mMApplication = application as App
        DaggerMineShareActivityComponent.builder()
                .appComponent(mMApplication.appComponent)
                .build()
                .injectMineShareActivity(this)
    }

    override fun onInitToolbar() {
        super.onInitToolbar()
        setSupportActionBar(binding.toolBarInclude.toolBar)
        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)
    }

    override fun onInitData() {
        binding.toolBarInclude.tvCenterTitle.setText(R.string.nv_share)
        binding.normalView.setOnRefreshListener(this)
        binding.normalView.setOnLoadMoreListener(this)
        mSkeletonScreen = Skeleton.bind(binding.rvShare)
                .adapter(mCollectArticleAdapter)
                .load(R.layout.recycle_mine_share_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(Config.skeletonDuration)
                .show()

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(this, R.drawable.rv_divider_bg)!!
        itemDecoration.setDrawable(drawable)
        binding.rvShare.addItemDecoration(itemDecoration)

        binding.rvShare.addOnItemTouchListener(object : OnRecycleItemClickListener(binding.rvShare) {
            override fun onRecycleItemClick(view: View, position: Int, holder: RecyclerView.ViewHolder) {
                val bean = mCollectArticleAdapter.getItem(holder.adapterPosition)
                ArticleDetailActivity.startArticleDetailActivity(this@MineShareActivity, bean.id, bean.link, bean.isCollect)
            }
        })
        binding.normalView.autoRefresh()
    }

    override fun onPresenter(): ShareContract.Presenter {
        return mCollPresenter
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPageIndex = 0
        mCollPresenter.getMineShareArticle(mPageIndex)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPageIndex++
        mCollPresenter.getMineShareArticle(mPageIndex)
    }

    override fun onGetShareArticleSuccess(list: List<Article>) {
        L.i(TAG, "onGetShareArticleSuccess")
        onShowNormalContent()
        if (mPageIndex == 0) {
            mSkeletonScreen.hide()
            mCollectArticleAdapter.clear()
            if (list.isEmpty()) {
                onShowEmptyMessage()
                binding.normalView.setEnableLoadMore(false)
            }
        }
        mCollectArticleAdapter.addAll(list)
        if (list.isEmpty()) binding.normalView.setEnableLoadMore(false)
        binding.normalView.finishRefresh()
        binding.normalView.finishLoadMore()
    }

    override fun onShowErrorMessage(message: String) {
        super.onShowErrorMessage(message)
        mSkeletonScreen.hide()
        binding.normalView.finishRefresh()
        binding.normalView.finishLoadMore()
    }

    companion object {
        private val TAG = MineShareActivity::class.java.simpleName
    }
}
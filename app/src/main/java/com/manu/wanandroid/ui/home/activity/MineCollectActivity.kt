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
import com.manu.wanandroid.bean.Collect
import com.manu.wanandroid.common.Config
import com.manu.wanandroid.contract.home.CollectContract
import com.manu.wanandroid.databinding.ActivityMineCollectBinding
import com.manu.wanandroid.di.component.activity.DaggerMineCollectActivityComponent
import com.manu.wanandroid.presenter.home.MineCollectPresenter
import com.manu.wanandroid.ui.home.adapter.MineCollectAdapter
import com.manu.wanandroid.utils.L
import com.manu.wanandroid.utils.StatusBarUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import javax.inject.Inject

/**
 * @Desc: 我的收藏
 * @Author: jzman
 */
class MineCollectActivity : BaseLoadMvpActivity<CollectContract.Presenter?>(), CollectContract.View,
        OnRefreshLoadMoreListener, OnLoadMoreListener {
    @Inject
    lateinit var mCollectArticleAdapter: MineCollectAdapter

    @Inject
    lateinit var mCollPresenter: MineCollectPresenter

    private lateinit var binding: ActivityMineCollectBinding
    private lateinit var mSkeletonScreen: SkeletonScreen
    private var mPageIndex = 0

    override fun onLayout(): View {
        binding = ActivityMineCollectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {
        val mMApplication = application as App
        DaggerMineCollectActivityComponent.builder()
                .appComponent(mMApplication.appComponent)
                .build()
                .injectMineCollectActivity(this)
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
        binding.toolBarInclude.tvCenterTitle.setText(R.string.nv_collect)
        binding.normalView.setOnRefreshListener(this)
        binding.normalView.setOnLoadMoreListener(this)
        mSkeletonScreen = Skeleton.bind(binding.rvCollect)
                .adapter(mCollectArticleAdapter)
                .load(R.layout.recycle_home_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(Config.skeletonDuration)
                .show()

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(this, R.drawable.rv_divider_bg)!!
        itemDecoration.setDrawable(drawable)
        binding.rvCollect.addItemDecoration(itemDecoration)

        binding.rvCollect.addOnItemTouchListener(object : OnRecycleItemClickListener(binding.rvCollect) {
            override fun onRecycleItemClick(view: View, position: Int, holder: RecyclerView.ViewHolder) {
                val bean = mCollectArticleAdapter.getItem(holder.adapterPosition)
                ArticleDetailActivity.startArticleDetailActivityForResult(
                        this@MineCollectActivity, bean.originId, bean.link, true) {
                    val intent = it.data ?: return@startArticleDetailActivityForResult
                    val isRefresh: Boolean = intent.getBooleanExtra(
                            ArticleDetailActivity.ARTICLE_REFRESH, false)
                    if (it.resultCode == RESULT_OK && isRefresh) {
                        binding.normalView.autoRefresh()
                    }
                }
            }
        })
        binding.normalView.autoRefresh()
    }

    override fun onPresenter(): CollectContract.Presenter {
        return mCollPresenter
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPageIndex = 0
        mCollPresenter.getCollectArticle(mPageIndex)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPageIndex++
        mCollPresenter.getCollectArticle(mPageIndex)
    }

    override fun onCollectArticleSuccess() {}
    override fun onUnCollectArticleSuccess() {}
    override fun onGetCollectArticleSuccess(result: List<Collect>) {
        L.i(TAG, "onGetCollectArticleSuccess")
        onShowNormalContent()
        if (mPageIndex == 0) {
            mSkeletonScreen.hide()
            mCollectArticleAdapter.clear()
            if (result.isEmpty()) {
                onShowEmptyMessage()
                binding.normalView.setEnableLoadMore(false)
            }
        }
        mCollectArticleAdapter.addAll(result)
        if (result.isEmpty()) binding.normalView.setEnableLoadMore(false)
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
        private val TAG = MineCollectActivity::class.java.simpleName
    }
}
package com.manu.wanandroid.ui.home.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.manu.wanandroid.R
import com.manu.wanandroid.app.MApplication
import com.manu.wanandroid.base.activity.BaseLoadMvpActivity
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener
import com.manu.wanandroid.bean.Collect
import com.manu.wanandroid.contract.home.CollectContract
import com.manu.wanandroid.databinding.ActivityMineCollectBinding
import com.manu.wanandroid.di.component.activity.DaggerMineCollectActivityComponent
import com.manu.wanandroid.presenter.home.CollectPresenter
import com.manu.wanandroid.ui.home.adapter.CollectArticleAdapter
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
    lateinit var mCollectArticleAdapter: CollectArticleAdapter

    @Inject
    lateinit var mCollPresenter: CollectPresenter

    private lateinit var binding: ActivityMineCollectBinding
    private lateinit var mSkeletonScreen: SkeletonScreen
    private var mPageIndex = 0

    override fun onLayout(): View {
        binding = ActivityMineCollectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {
        val mMApplication = application as MApplication
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
        binding.rvCollect.layoutManager = LinearLayoutManager(this)
        mSkeletonScreen = Skeleton.bind(binding.rvCollect)
                .adapter(mCollectArticleAdapter)
                .load(R.layout.recycle_home_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(1500)
                .show()
        binding.rvCollect.addOnItemTouchListener(object : OnRecycleItemClickListener(binding.rvCollect) {
            override fun onRecycleItemClick(view: View, position: Int, holder: RecyclerView.ViewHolder) {
                val bean = mCollectArticleAdapter.getItem(holder.adapterPosition)
                ArticleDetailActivity.startArticleDetailActivity(this@MineCollectActivity, bean.id, bean.link, true)
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

        @JvmStatic
        fun startMineCollectActivity(context: Activity) {
            val intent = Intent(context, MineCollectActivity::class.java)
            context.startActivity(intent)
        }
    }
}
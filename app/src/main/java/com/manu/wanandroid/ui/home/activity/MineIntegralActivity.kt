package com.manu.wanandroid.ui.home.activity

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.manu.wanandroid.R
import com.manu.wanandroid.app.App
import com.manu.wanandroid.base.activity.BaseLoadMvpActivity
import com.manu.wanandroid.bean.Integral
import com.manu.wanandroid.bean.IntegralInfo
import com.manu.wanandroid.common.Account
import com.manu.wanandroid.common.Config.skeletonDuration
import com.manu.wanandroid.contract.home.IntegralContract
import com.manu.wanandroid.databinding.ActivityMineIntegralBinding
import com.manu.wanandroid.di.component.activity.DaggerMineIntegralActivityComponent
import com.manu.wanandroid.presenter.home.MineIntegralPresenter
import com.manu.wanandroid.ui.home.adapter.MineIntegralAdapter
import com.manu.wanandroid.utils.L
import com.manu.wanandroid.utils.StatusBarUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * @Desc: 我的积分
 * @Author: jzman
 */
class MineIntegralActivity : BaseLoadMvpActivity<IntegralContract.Presenter>(), IntegralContract.View,
        OnLoadMoreListener, CoroutineScope by MainScope() {
    @Inject
    lateinit var mMineIntegralAdapter: MineIntegralAdapter

    @Inject
    lateinit var mMineIntegralPresenter: MineIntegralPresenter

    private lateinit var binding: ActivityMineIntegralBinding
    private lateinit var mSkeletonScreen: SkeletonScreen
    private var mPageIndex = 1

    override fun onLayout(): View {
        binding = ActivityMineIntegralBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {
        val mMApplication = application as App
        DaggerMineIntegralActivityComponent.builder()
                .appComponent(mMApplication.appComponent)
                .build()
                .injectMineIntegralActivity(this)
    }

    override fun onToolbar() {
        super.onToolbar()
        binding.toolBar.setBackgroundColor(Color.TRANSPARENT)
        setSupportActionBar(binding.toolBar)
        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
        binding.ctlLayout.setCollapsedTitleTextColor(Color.WHITE)
        binding.ctlLayout.setExpandedTitleColor(Color.WHITE)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)
    }

    override fun onPresenter(): IntegralContract.Presenter {
        return mMineIntegralPresenter
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onData() {
        binding.normalView.setOnLoadMoreListener(this)
        binding.normalView.setEnableRefresh(false)

        mSkeletonScreen = Skeleton.bind(binding.rvIntegral)
                .adapter(mMineIntegralAdapter)
                .load(R.layout.recycle_mine_integral_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(skeletonDuration)
                .show()

        mMineIntegralPresenter.getMineIntegral(mPageIndex)
        val info = Account.getIntegralInfo() ?: return;

        launch {
            for (i in 1..info.coinCount) {
                if (i % 4 == 0 ){
                    delay(1)
                    binding.ctlLayout.title = "$i"
                }else{
                    binding.ctlLayout.title = "$i"
                }
            }
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPageIndex++
        mMineIntegralPresenter.getMineIntegral(mPageIndex)
    }

    override fun onGetMineIntegralSuccess(result: List<Integral>) {
        L.i(TAG, "onGetMineIntegralSuccess:$mPageIndex")
        onShowNormalContent()
        if (mPageIndex == 1) {
            mSkeletonScreen.hide()
            mMineIntegralAdapter.clear()
            if (result.isEmpty()) {
                onShowEmptyMessage()
                binding.normalView.setEnableLoadMore(false)
            }
        }
        mMineIntegralAdapter.addAll(result)
        if (result.isEmpty()) binding.normalView.setEnableLoadMore(false)
        binding.normalView.finishRefresh()
        binding.normalView.finishLoadMore()
    }

    override fun onGetMineIntegralInfoSuccess(result: IntegralInfo) {
    }

    override fun onShowErrorMessage(message: String) {
        super.onShowErrorMessage(message)
        mSkeletonScreen.hide()
        binding.normalView.finishRefresh()
        binding.normalView.finishLoadMore()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    companion object {
        private val TAG = MineCollectActivity::class.java.simpleName
    }
}
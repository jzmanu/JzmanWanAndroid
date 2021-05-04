package com.manu.wanandroid.ui.search.fragment

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.manu.wanandroid.R
import com.manu.wanandroid.base.activity.BaseLoadMvpActivity
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener
import com.manu.wanandroid.base.fragment.BaseMvpFragment
import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.bean.HotWord
import com.manu.wanandroid.common.Config
import com.manu.wanandroid.contract.search.SearchContract
import com.manu.wanandroid.databinding.FragmentSearchBinding
import com.manu.wanandroid.di.module.fragment.SearchFragmentModule
import com.manu.wanandroid.presenter.search.SearchPresenter
import com.manu.wanandroid.ui.home.activity.ArticleDetailActivity
import com.manu.wanandroid.ui.search.activity.SearchActivity
import com.manu.wanandroid.ui.search.adapter.SearchArticleAdapter
import com.manu.wanandroid.utils.L
import com.moxun.tagcloudlib.view.TagsAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import javax.inject.Inject

class SearchFragment : BaseMvpFragment<SearchContract.Presenter>(), SearchContract.View,
        OnRefreshLoadMoreListener, OnLoadMoreListener {
    @Inject
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var mSearchArticleAdapter: SearchArticleAdapter
    private lateinit var binding: FragmentSearchBinding
    private lateinit var mSkeletonScreen: SkeletonScreen
    private lateinit var mActivity: SearchActivity
    private var tagCloud = ArrayList<HotWord>()
    private var tagCloudString = ArrayList<String>()
    private var mPageIndex = 0
    private var key = ""

    override fun onLayout(): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {
        mActivity = activity as SearchActivity
        mActivity.searchActivityComponent
                .getSearchFragmentComponent(SearchFragmentModule())
                .injectSearchFragment(this)
    }

    override fun onData() {
        presenter.hotWords()
        binding.normalView.setOnRefreshListener(this)
        binding.normalView.setOnLoadMoreListener(this)

        val itemDecoration = DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(mActivity, R.drawable.rv_divider_bg)!!
        itemDecoration.setDrawable(drawable)
        binding.rvHome.addItemDecoration(itemDecoration)

        mSkeletonScreen = Skeleton.bind(binding.rvHome)
                .adapter(mSearchArticleAdapter)
                .load(R.layout.recycle_home_item_article_skeleton)
                .color(R.color.colorAnimator)
                .duration(Config.skeletonDuration)
                .show()

        binding.rvHome.addOnItemTouchListener(object : OnRecycleItemClickListener(binding.rvHome) {
            override fun onRecycleItemClick(view: View, position: Int, holder: RecyclerView.ViewHolder) {
                val bean = mSearchArticleAdapter.getItem(holder.adapterPosition) as Article
                ArticleDetailActivity.startArticleDetailActivityForResult(
                        mActivity, bean.id, bean.link, true) {
                    val intent = it.data ?: return@startArticleDetailActivityForResult
                    val isRefresh: Boolean = intent.getBooleanExtra(
                            ArticleDetailActivity.ARTICLE_REFRESH, false)
                    if (it.resultCode == BaseLoadMvpActivity.RESULT_OK && isRefresh) {
                        binding.normalView.autoRefresh()
                    }
                }
            }
        })
        binding.normalView.autoRefresh()
    }

    override fun onPresenter(): SearchContract.Presenter {
        return presenter
    }

    override fun onHotWordsSuccess(result: List<HotWord>) {
        L.i(TAG, "onHotWordsSuccess:$result")
        tagCloud = result as ArrayList<HotWord>
        // 数据偏少，添加两次
        result.forEach {
            tagCloudString.add(it.name)
        }
        result.forEach {
            tagCloudString.add(it.name)
        }
        L.i(TAG, "onHotWordsSuccess:$tagCloudString")
        val tagAdapter = TagCloudAdapter(tagCloudString)
        binding.tagCloud.setAdapter(tagAdapter)
    }

    override fun onSearchSuccess(result: List<Article>) {
        L.i(TAG, "onSearchSuccess:$result")
        onShowNormalContent()
        if (mPageIndex == 0) {
            mSkeletonScreen.hide()
            mSearchArticleAdapter.clear()
            if (result.isEmpty()) {
                onShowEmptyMessage()
                binding.normalView.setEnableLoadMore(false)
            }
        }
        mSearchArticleAdapter.addAll(result)
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

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPageIndex = 0
        presenter.search(mPageIndex, key)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPageIndex++
        presenter.search(mPageIndex, key)
    }

    fun search(key: String) {
        mPageIndex = 0
        this.key = key
        presenter.search(mPageIndex, key)
    }

    companion object {
        private const val TAG = "TagCloudFragment"
    }

    // TODO: 后续完成
    inner class TagCloudAdapter(private val tagList: List<String>) : TagsAdapter() {

        override fun getCount(): Int {
            return tagList.size
        }

        override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {
            val tv = TextView(context)
            tv.setPadding(8)
            tv.text = tagList[position]
            tv.setTextColor(Color.WHITE)
            tv.gravity = Gravity.CENTER
            tv.setOnClickListener {
            }
            return tv
        }

        override fun getItem(position: Int): Any {
            return tagList[position]
        }

        override fun getPopularity(position: Int): Int {
            return position % 7
        }

        override fun onThemeColorChanged(view: View?, themeColor: Int) {
            view?.setBackgroundColor(themeColor);
        }
    }
}
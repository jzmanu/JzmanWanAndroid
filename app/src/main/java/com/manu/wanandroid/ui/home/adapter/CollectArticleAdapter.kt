package com.manu.wanandroid.ui.home.adapter

import android.view.ViewGroup
import com.manu.wanandroid.base.adapter.SingleRecyclerViewAdapter
import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.databinding.RecycleHomeItemArticleBinding

/**
 * @Desc: 收藏文章列表Adapter
 * @Author: jzman
 * @Date: 2021/1/24 23:42.
 */
class CollectArticleAdapter : SingleRecyclerViewAdapter<Article,RecycleHomeItemArticleBinding>() {

    override fun getItemViewType(data: Article?, position: Int): Int {
        TODO("Not yet implemented")
    }

    override fun onBinding(viewGroup: ViewGroup, viewType: Int): RecycleHomeItemArticleBinding {
        TODO("Not yet implemented")
    }

    override fun onBindData(holder: BindingViewHolder<RecycleHomeItemArticleBinding>?, position: Int, bean: Article?, viewType: Int) {
        TODO("Not yet implemented")
    }
}
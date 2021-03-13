package com.manu.wanandroid.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import com.manu.wanandroid.R
import com.manu.wanandroid.base.adapter.SingleRecyclerViewAdapter
import com.manu.wanandroid.bean.Article
import com.manu.wanandroid.databinding.RecycleHomeItemArticleBinding

/**
 * @Desc: 我的分享
 * @Author: jzman
 * @Date: 2021/1/24
 */
class MineShareAdapter : SingleRecyclerViewAdapter<Article, RecycleHomeItemArticleBinding>() {

    override fun getItemViewType(data: Article?, position: Int): Int {
        return R.layout.recycle_home_item_article
    }

    override fun onBinding(viewGroup: ViewGroup, viewType: Int): RecycleHomeItemArticleBinding {
        return RecycleHomeItemArticleBinding.inflate(mLayoutInflater, viewGroup, false)
    }

    override fun onBindData(holder: BindingViewHolder<RecycleHomeItemArticleBinding>, position: Int, bean: Article?, viewType: Int) {
        holder.binding.tvItemAuthor.visibility = View.GONE
        holder.binding.tvItemTitle.text = bean?.title
        holder.binding.tvItemCategory.text = bean?.chapterName
        holder.binding.tvItemDate.text = bean?.niceDate
    }
}
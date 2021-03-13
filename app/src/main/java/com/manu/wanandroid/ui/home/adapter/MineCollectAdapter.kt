package com.manu.wanandroid.ui.home.adapter

import android.view.ViewGroup
import com.manu.wanandroid.R
import com.manu.wanandroid.base.adapter.SingleRecyclerViewAdapter
import com.manu.wanandroid.bean.Collect
import com.manu.wanandroid.databinding.RecycleHomeItemArticleBinding

/**
 * @Desc: 我的收藏
 * @Author: jzman
 * @Date: 2021/1/24
 */
class MineCollectAdapter : SingleRecyclerViewAdapter<Collect, RecycleHomeItemArticleBinding>() {

    override fun getItemViewType(data: Collect?, position: Int): Int {
        return R.layout.recycle_home_item_article
    }

    override fun onBinding(viewGroup: ViewGroup, viewType: Int): RecycleHomeItemArticleBinding {
        return RecycleHomeItemArticleBinding.inflate(mLayoutInflater, viewGroup, false)
    }

    override fun onBindData(holder: BindingViewHolder<RecycleHomeItemArticleBinding>, position: Int, bean: Collect, viewType: Int) {
        val author = if (bean.author.isEmpty()) "匿名" else bean.author
        holder.binding.tvItemAuthor.text = author
        holder.binding.tvItemTitle.text = bean.title
        holder.binding.tvItemCategory.text = bean.chapterName
        holder.binding.tvItemDate.text = bean.niceDate
    }
}
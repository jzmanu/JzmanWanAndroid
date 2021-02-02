package com.manu.wanandroid.ui.home.adapter

import android.view.ViewGroup
import com.manu.wanandroid.R
import com.manu.wanandroid.base.adapter.SingleRecyclerViewAdapter
import com.manu.wanandroid.bean.Integral
import com.manu.wanandroid.databinding.RecycleMineIntegralItemArticleBinding

/**
 * @Desc: 我的积分
 * @Author: jzman
 * @Date: 2021/2/1
 */
class IntegralArticleAdapter : SingleRecyclerViewAdapter<Integral, RecycleMineIntegralItemArticleBinding>() {

    override fun getItemViewType(data: Integral?, position: Int): Int {
        return R.layout.recycle_mine_integral_item_article
    }

    override fun onBinding(viewGroup: ViewGroup, viewType: Int): RecycleMineIntegralItemArticleBinding {
        return RecycleMineIntegralItemArticleBinding.inflate(mLayoutInflater, viewGroup, false)
    }

    override fun onBindData(holder: BindingViewHolder<RecycleMineIntegralItemArticleBinding>, position: Int, bean: Integral?, viewType: Int) {
        holder.binding.tvItemReason.text = bean?.desc
        holder.binding.tvItemDate.text = bean?.date.toString()
    }
}
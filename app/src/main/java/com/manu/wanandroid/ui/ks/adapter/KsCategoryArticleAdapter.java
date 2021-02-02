package com.manu.wanandroid.ui.ks.adapter;

import android.app.LauncherActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.SingleRecyclerViewAdapter;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.databinding.RecycleHomeItemArticleBinding;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * @Desc: KsCategoryArticleAdapter
 * @Author: jzman
 * @Date: 2019/5/31 0031 15:13
 */
public class KsCategoryArticleAdapter extends SingleRecyclerViewAdapter<Article,RecycleHomeItemArticleBinding> {

    @Override
    protected int getItemViewType(Article data, int position) {
        return R.layout.recycle_home_item_article;
    }

    @Override
    protected RecycleHomeItemArticleBinding onBinding(@NonNull ViewGroup viewGroup, int viewType) {
        RecycleHomeItemArticleBinding binding =
                RecycleHomeItemArticleBinding.inflate(mLayoutInflater, viewGroup, false);
        return binding;
    }

    @Override
    public void onBindData(@NotNull BindingViewHolder<RecycleHomeItemArticleBinding> holder, int position, Article bean, int viewType) {
        String author = bean.getAuthor() ;
        if (TextUtils.isEmpty(author)){
            author = bean.getShareUser();
            if (TextUtils.isEmpty(author))
                holder.getBinding().tvItemAuthor.setVisibility(View.GONE);
        }
        holder.getBinding().tvItemAuthor.setText(author);
        holder.getBinding().tvItemTitle.setText(Html.fromHtml(bean.getTitle()));
        holder.getBinding().tvItemCategory.setText(bean.getSuperChapterName());
        holder.getBinding().tvItemDate.setText(bean.getNiceDate());
    }
}

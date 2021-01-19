package com.manu.wanandroid.ui.ks.adapter;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.BaseRecyclerViewAdapter;
import com.manu.wanandroid.base.adapter.RecyclerViewHolder;
import com.manu.wanandroid.bean.Article;

/**
 * @Desc: KsCategoryArticleAdapter
 * @Author: jzman
 * @Date: 2019/5/31 0031 15:13
 */
public class KsCategoryArticleAdapter extends BaseRecyclerViewAdapter<Article> {
    @Override
    public int onLayoutId() {
        return R.layout.recycle_home_item_article;
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, int position, Article bean) {
        holder.setText(R.id.tv_item_author, getText(bean.getAuthor()))
                .setText(R.id.tv_item_title, getText(bean.getTitle()))
                .setText(R.id.tv_item_category, getText(bean.getSuperChapterName()))
                .setText(R.id.tv_item_date, getText(bean.getNiceDate()));
    }
}

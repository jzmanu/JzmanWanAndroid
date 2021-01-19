package com.manu.wanandroid.ui.home.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.BaseRecyclerViewAdapter;
import com.manu.wanandroid.base.adapter.RecyclerViewHolder;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.utils.L;
import com.youth.banner.Banner;

import androidx.annotation.NonNull;

/**
 * @Desc: HomeArticleAdapter
 * @Author: jzman
 * @Date: 2019/5/10 0010 13:09
 */
public class HomeArticleAdapter extends BaseRecyclerViewAdapter<Article> {

    private static final int HEADER = 0;
    private static final int CONTENT = 1;

    private OnBannerListener mOnBannerListener;

    public void setOnBannerListener(OnBannerListener mOnBannerListener) {
        this.mOnBannerListener = mOnBannerListener;
    }

    @Override
    public int onLayoutId() {
        return R.layout.recycle_home_item_article;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == HEADER) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_banner_header, viewGroup, false);
            return new HeaderViewHolder(view);
        } else if (i == CONTENT) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_home_item_article, viewGroup, false);
            return new ContentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, int position, Article bean) {
        L.i("Home","onBindData > position:"+position);
        if (holder instanceof HeaderViewHolder) {
            Banner mBanner = (Banner) holder.getView(R.id.home_banner);
            if (mOnBannerListener!=null) mOnBannerListener.onBannerInit(mBanner);
        } else if (holder instanceof ContentViewHolder) {
            Article articleBean = getItem(position-1);
            String author = TextUtils.isEmpty(articleBean.getAuthor()) ?
                    articleBean.getShareUser():articleBean.getAuthor();
            holder.setText(R.id.tv_item_author, getText(author))
                    .setText(R.id.tv_item_title, getText(articleBean.getTitle()))
                    .setText(R.id.tv_item_category, getText(articleBean.getSuperChapterName()+"/"+articleBean.getChapterName()))
                    .setText(R.id.tv_item_date, getText(articleBean.getNiceDate()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return CONTENT;
        }
    }

    public static class HeaderViewHolder extends RecyclerViewHolder {
        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class ContentViewHolder extends RecyclerViewHolder {
        ContentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnBannerListener{
        void onBannerInit(Banner banner);
    }

}

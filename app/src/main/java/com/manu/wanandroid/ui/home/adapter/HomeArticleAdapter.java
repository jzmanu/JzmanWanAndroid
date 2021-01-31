package com.manu.wanandroid.ui.home.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.BaseRecyclerViewAdapter;
import com.manu.wanandroid.base.adapter.RecyclerViewHolder;
import com.manu.wanandroid.bean.Article;
import com.manu.wanandroid.databinding.HomeBannerHeaderBinding;
import com.manu.wanandroid.databinding.RecycleHomeItemArticleBinding;
import com.manu.wanandroid.databinding.RecycleProjectItemArticleBinding;
import com.manu.wanandroid.utils.image.ImageLoaderHelper;
import com.youth.banner.Banner;

import androidx.annotation.NonNull;

/**
 * @Desc: HomeArticleAdapter
 * @Author: jzman
 * @Date: 2019/5/10
 */
public class HomeArticleAdapter extends BaseRecyclerViewAdapter<Article> {

    private OnBannerListener mOnBannerListener;

    public void setOnBannerListener(OnBannerListener mOnBannerListener) {
        this.mOnBannerListener = mOnBannerListener;
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, int position, Article bean, int viewType) {
        if (holder instanceof HeaderViewHolder) {
            Banner mBanner = ((HeaderViewHolder) holder).binding.homeBanner;
            if (mOnBannerListener != null) mOnBannerListener.onBannerInit(mBanner);
        } else if (holder instanceof ContentViewHolder) {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            Article articleBean = getItem(position - 1);
            String author = TextUtils.isEmpty(articleBean.getAuthor()) ?
                    articleBean.getShareUser() : articleBean.getAuthor();
            viewHolder.binding.tvItemAuthor.setText(author);
            viewHolder.binding.tvItemTitle.setText(articleBean.getTitle());
            viewHolder.binding.tvItemCategory.setText(String.format("%s/%s", articleBean.getSuperChapterName(), articleBean.getChapterName()));
            viewHolder.binding.tvItemDate.setText(articleBean.getNiceDate());
        }else if(holder instanceof ProjectViewHolder){
            ProjectViewHolder viewHolder = (ProjectViewHolder) holder;
            ImageLoaderHelper.getInstance().showImageForNet(getRecyclerView().getContext(),bean.getEnvelopePic(),viewHolder.binding.ivItemPreview);
            viewHolder.binding.tvItemProTitle.setText(bean.getTitle());
            viewHolder.binding.tvItemProDesc.setText(bean.getDesc());
            viewHolder.binding.tvItemProAuthor.setText(bean.getAuthor());
            viewHolder.binding.tvItemProDate.setText(bean.getNiceDate());
        }
    }

    @Override
    protected int getItemViewType(Article data, int position) {
        if (position == 0) {
            return R.layout.home_banner_header;
        } else if (data.getChapterId() == 294) {
            // 完整项目
            return R.layout.recycle_project_item_article;
        } else {
            return R.layout.recycle_home_item_article;
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.home_banner_header) {
            HomeBannerHeaderBinding binding = HomeBannerHeaderBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new HeaderViewHolder(binding);
        } else if (viewType == R.layout.recycle_project_item_article) {
            RecycleProjectItemArticleBinding binding = RecycleProjectItemArticleBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new ProjectViewHolder(binding);
        } else {
            RecycleHomeItemArticleBinding binding = RecycleHomeItemArticleBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new ContentViewHolder(binding);
        }
    }

    public static class HeaderViewHolder extends RecyclerViewHolder {
        private final HomeBannerHeaderBinding binding;

        public HeaderViewHolder(HomeBannerHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ContentViewHolder extends RecyclerViewHolder {
        private final RecycleHomeItemArticleBinding binding;

        ContentViewHolder(RecycleHomeItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ProjectViewHolder extends RecyclerViewHolder {
        private final RecycleProjectItemArticleBinding binding;

        ProjectViewHolder(RecycleProjectItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnBannerListener {
        void onBannerInit(Banner banner);
    }
}

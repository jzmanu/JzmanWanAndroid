package com.manu.wanandroid.ui.project.adapter;

import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.SingleRecyclerViewAdapter;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.databinding.RecycleProjectItemArticleBinding;
import com.manu.wanandroid.utils.image.ImageLoaderHelper;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * @Desc: ProjectArticleAdapter
 * @Author: jzman
 * @Date: 2019/5/30 0030 14:14
 */
public class ProjectArticleAdapter extends SingleRecyclerViewAdapter<Project, RecycleProjectItemArticleBinding> {
    @Override
    protected int getItemViewType(Project data, int position) {
        return R.layout.recycle_project_item_article;
    }

    @Override
    protected RecycleProjectItemArticleBinding onBinding(@NonNull ViewGroup viewGroup, int viewType) {
        RecycleProjectItemArticleBinding binding =
                RecycleProjectItemArticleBinding.inflate(mLayoutInflater,viewGroup,false);
        return binding;
    }

    @Override
    protected void onBindData(@NotNull BindingViewHolder<RecycleProjectItemArticleBinding> holder, int position, Project bean, int viewType) {
        ImageLoaderHelper.getInstance().showImageForNet(mContext,bean.getEnvelopePic(),holder.getBinding().ivItemPreview);
        holder.getBinding().tvItemProTitle.setText(bean.getTitle());
        holder.getBinding().tvItemProDesc.setText(bean.getDesc());
        holder.getBinding().tvItemProAuthor.setText(bean.getAuthor());
        holder.getBinding().tvItemProDate.setText(bean.getNiceDate());
    }
}

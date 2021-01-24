package com.manu.wanandroid.ui.project.adapter;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.BaseRecyclerViewAdapter;
import com.manu.wanandroid.base.adapter.RecyclerViewHolder;
import com.manu.wanandroid.bean.Project;
import com.manu.wanandroid.utils.image.ImageLoaderHelper;

/**
 * @Desc: ProjectArticleAdapter
 * @Author: jzman
 * @Date: 2019/5/30 0030 14:14
 */
public class ProjectArticleAdapter extends BaseRecyclerViewAdapter<Project> {

    @Override
    public int onLayoutId() {
        return R.layout.recycle_project_item_article;
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, int position, Project bean) {
        ImageLoaderHelper.getInstance().showImageForNet(mContext,bean.getEnvelopePic(),holder.getImageView(R.id.iv_item_preview));
        holder.setText(R.id.tv_item_pro_title,bean.getTitle());
        holder.setText(R.id.tv_item_pro_desc,bean.getDesc());
        holder.setText(R.id.tv_item_pro_author,bean.getAuthor());
        holder.setText(R.id.tv_item_pro_date,bean.getNiceDate());
    }
}

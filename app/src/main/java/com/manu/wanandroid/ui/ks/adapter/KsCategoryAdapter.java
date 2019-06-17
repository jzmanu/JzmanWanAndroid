package com.manu.wanandroid.ui.ks.adapter;

import android.graphics.Color;
import android.util.SparseBooleanArray;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.BaseRecyclerViewAdapter;
import com.manu.wanandroid.base.adapter.RecyclerViewHolder;
import com.manu.wanandroid.ui.ks.bean.KsBean;

/**
 * @Desc: KsCategoryAdapter
 * @Author: jzman
 * @Date: 2019/5/31 0031 13:46
 */
public class KsCategoryAdapter extends BaseRecyclerViewAdapter<KsBean> {
    private SparseBooleanArray mSelectSparseArray;

    public KsCategoryAdapter() {
        super();
        mSelectSparseArray = new SparseBooleanArray();
    }

    @Override
    public int onLayoutId() {
        return R.layout.recycle_ks_item_first_category;
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, int position, KsBean bean) {
        holder.setText(R.id.tv_item_ks_left_title, bean.getName());
        if (mSelectSparseArray.get(position)) {
            holder.setBackgroundColor(R.id.ll_item_ks_left_title, Color.parseColor("#008577"))
                    .setTextColor(R.id.tv_item_ks_left_title, Color.WHITE);
        } else {
            holder.setBackgroundColor(R.id.ll_item_ks_left_title, Color.WHITE)
                    .setTextColor(R.id.tv_item_ks_left_title, Color.GRAY);
        }
    }

    public void setSelectPosition(int position) {
        int size = getData().size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (position == i) {
                    mSelectSparseArray.put(i, true);
                } else {
                    mSelectSparseArray.put(i, false);
                }
            }
            notifyDataSetChanged();
        }
    }
}

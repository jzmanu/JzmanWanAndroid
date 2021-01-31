package com.manu.wanandroid.ui.ks.adapter;

import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.SingleRecyclerViewAdapter;
import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.databinding.RecycleKsItemFirstCategoryBinding;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * @Desc: KsCategoryAdapter
 * @Author: jzman
 * @Date: 2019/5/31 0031 13:46
 */
public class KsCategoryAdapter extends SingleRecyclerViewAdapter<Knowledge, RecycleKsItemFirstCategoryBinding> {
    private final SparseBooleanArray mSelectSparseArray;

    public KsCategoryAdapter() {
        super();
        mSelectSparseArray = new SparseBooleanArray();
    }

    @Override
    public void onBindData(@NotNull BindingViewHolder<RecycleKsItemFirstCategoryBinding> holder, int position, Knowledge bean, int viewType) {
        holder.getBinding().tvItemKsLeftTitle.setText(bean.getName());
        if (mSelectSparseArray.get(position)) {
            holder.getBinding().llItemKsLeftTitle.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
            holder.getBinding().tvItemKsLeftTitle.setTextColor(Color.WHITE);
        } else {
            holder.getBinding().llItemKsLeftTitle.setBackgroundColor(Color.WHITE);
            holder.getBinding().tvItemKsLeftTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    protected int getItemViewType(Knowledge data, int position) {
        return R.layout.recycle_ks_item_first_category;
    }

    @Override
    protected RecycleKsItemFirstCategoryBinding onBinding(@NonNull ViewGroup viewGroup, int viewType) {
        RecycleKsItemFirstCategoryBinding binding =
                RecycleKsItemFirstCategoryBinding.inflate(mLayoutInflater,viewGroup,false);
        return binding;
    }

    public void setSelectPosition(int position) {
        int size = getData().size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mSelectSparseArray.put(i, position == i);
            }
            notifyDataSetChanged();
        }
    }
}

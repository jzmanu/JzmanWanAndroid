package com.manu.wanandroid.base.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Desc: BaseRecyclerViewAdapter
 * @Author: jzman
 * @Date: 2019/5/10 0010 11:38
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private static final String TAG = BaseRecyclerViewAdapter.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private final List<T> mData;

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @LayoutRes
    protected abstract int getItemViewType(T data, int position);

    protected abstract void onBindData(RecyclerViewHolder holder, int position, T bean, int viewType);

    public BaseRecyclerViewAdapter() {
        this.mData = new ArrayList<>();
    }

    public BaseRecyclerViewAdapter(@NonNull List<T> mData) {
        this.mData = mData;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mRecyclerView != null) mRecyclerView = null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        onBindData(holder, i, getItem(i), getItemViewType(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(mData.get(position), position);
    }

    public void add(T t) {
        mData.add(t);
        notifyDataSetChanged();
    }

    public void add(int position, T t) {
        mData.add(position, t);
        notifyItemChanged(position);
    }

    public void addAllOnly(List<T> data) {
        mData.addAll(data);
    }

    public void addAll(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    public T getItem(int position) {
        return mData.get(position);
    }
}

package com.manu.wanandroid.base.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
    protected Context mContext;
    private RecyclerView mRecyclerView;
    private List<T> mData;

    public abstract @LayoutRes
    int onLayoutId();

    public abstract void onBindData(RecyclerViewHolder holder, int position, T bean);

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

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.mContext = viewGroup.getContext();
        RecyclerViewHolder holder = new RecyclerViewHolder(LayoutInflater.from(mContext).inflate(onLayoutId(), viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        onBindData(holder, i, getItem(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(T t) {
        mData.add(t);
        notifyDataSetChanged();
    }

    public void add(int position, T t) {
        mData.add(position, t);
        notifyItemChanged(position);
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

    protected String getText(String text) {
        return TextUtils.isEmpty(text) ? "" : text;
    }
}

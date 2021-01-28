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
import androidx.viewbinding.ViewBinding;

/**
 * @Desc: SingleRecyclerViewAdapter
 * @Author: jzman
 * @Date: 2019/5/10 0010 11:38
 */
public abstract class SingleRecyclerViewAdapter<T, B extends ViewBinding> extends
        RecyclerView.Adapter<SingleRecyclerViewAdapter.BindingViewHolder<B>> {
    private static final String TAG = SingleRecyclerViewAdapter.class.getSimpleName();
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private final List<T> mData;

    @LayoutRes
    protected abstract int getItemViewType(T data, int position);

    protected abstract B onBinding(@NonNull ViewGroup viewGroup, int viewType);

    protected abstract void onBindData(BindingViewHolder<B> holder, int position, T bean, int viewType);

    public SingleRecyclerViewAdapter() {
        this.mData = new ArrayList<>();
    }

    public SingleRecyclerViewAdapter(@NonNull List<T> mData) {
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
    public BindingViewHolder<B> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        this.mContext = viewGroup.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        BindingViewHolder<B> holder = new BindingViewHolder<>(onBinding(viewGroup,viewType));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int i) {
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

    protected String getText(String text) {
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public static class BindingViewHolder<B extends ViewBinding> extends RecyclerViewHolder {
        private final B binding;
        public BindingViewHolder(B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public B getBinding() {
            return binding;
        }
    }
}

package com.manu.wanandroid.base.adapter;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Desc: OnRecycleItemClickListener
 * @Author: jzman
 * @Date: 2019/5/10 0010 11:37
 */
public abstract class OnRecycleItemClickListener extends RecyclerView.SimpleOnItemTouchListener {
    private GestureDetector mGestureDetector;

    public OnRecycleItemClickListener(final RecyclerView rv) {
        mGestureDetector = new GestureDetector(rv.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View itemView = rv.findChildViewUnder(e.getX(), e.getY());
                if (itemView != null) {
                    RecyclerView.ViewHolder holder = rv.getChildViewHolder(itemView);
                    onRecycleItemClick(itemView, rv.getChildAdapterPosition(itemView), holder);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View itemView = rv.findChildViewUnder(e.getX(), e.getY());
                if (itemView != null) {
                    RecyclerView.ViewHolder holder = rv.getChildViewHolder(itemView);
                    onRecycleLongItemClick(itemView, rv.getChildAdapterPosition(itemView), holder);
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    public abstract void onRecycleItemClick(View view, int position, RecyclerView.ViewHolder holder);

    public void onRecycleLongItemClick(View view, int position, RecyclerView.ViewHolder holder) {
    }
}

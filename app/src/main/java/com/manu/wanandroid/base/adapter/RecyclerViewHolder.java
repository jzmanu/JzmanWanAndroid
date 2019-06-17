package com.manu.wanandroid.base.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Desc: RecyclerViewHolder
 * @Author: jzman
 * @Date: 2019/5/10 0010 13:14
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
    }

    public RecyclerViewHolder setText(@IdRes int textViewResId,String text){
        getTextView(textViewResId).setText(text);
        return this;
    }

    public RecyclerViewHolder setText(@IdRes int textViewResId, @StringRes int textId){
        getTextView(textViewResId).setText(textId);
        return this;
    }

    public RecyclerViewHolder setTextColor(@IdRes int textViewResId, String color){
        getTextView(textViewResId).setTextColor(Color.parseColor(color));
        return this;
    }

    public RecyclerViewHolder setTextColor(@IdRes int textViewResId, @ColorInt int colorResId){
        getTextView(textViewResId).setTextColor(colorResId);
        return this;
    }

    public RecyclerViewHolder setBackgroundResource(@IdRes int viewResId, @DrawableRes int drawableResId){
        getView(viewResId).setBackgroundResource(drawableResId);
        return this;
    }

    public RecyclerViewHolder setBackground(@IdRes int viewResId, Drawable drawable){
        getView(viewResId).setBackground(drawable);
        return this;
    }

    public RecyclerViewHolder setBackgroundColor(@IdRes int viewResId, @ColorInt int color){
        getView(viewResId).setBackgroundColor(color);
        return this;
    }

    public View getView(@IdRes int viewId) {
        return findViewById(viewId);
    }

    private TextView getTextView(@IdRes int viewId) {
        return findViewById(viewId);
    }

    public ImageView getImageView(@IdRes int viewId) {
        return findViewById(viewId);
    }

    private CheckBox getCheckBox(@IdRes int viewId) {
        return findViewById(viewId);
    }

    private EditText getEditText(@IdRes int viewId) {
        return findViewById(viewId);
    }

    private Button getButton(@IdRes int viewId) {
        return findViewById(viewId);
    }

    private <T extends View> T findViewById(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

}

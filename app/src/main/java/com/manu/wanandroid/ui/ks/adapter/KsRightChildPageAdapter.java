package com.manu.wanandroid.ui.ks.adapter;

import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.ui.ks.fragment.KsRightArticleFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @Desc: KsRightChildPageAdapter
 * @Author: jzman
 * @Date: 2019/6/4 0004 11:02
 */
public class KsRightChildPageAdapter extends FragmentStatePagerAdapter {
    private List<Knowledge.ChildrenBean> mChildrenBeans;

    public KsRightChildPageAdapter(@NonNull FragmentManager fm, List<Knowledge.ChildrenBean> beans) {
        super(fm);
        this.mChildrenBeans = beans;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return KsRightArticleFragment.newInstance(mChildrenBeans.get(position).getId());
    }

    @Override
    public int getCount() {
        return mChildrenBeans.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mChildrenBeans.get(position).getName();
    }
}

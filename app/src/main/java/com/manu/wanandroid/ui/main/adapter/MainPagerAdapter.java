package com.manu.wanandroid.ui.main.adapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @Desc: MainPagerAdapter
 * @Author: jzman
 * @Date: 2019/5/9 0009 15:29
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public MainPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}

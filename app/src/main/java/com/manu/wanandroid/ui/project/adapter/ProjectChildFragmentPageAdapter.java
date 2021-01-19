package com.manu.wanandroid.ui.project.adapter;

import android.view.ViewGroup;

import com.manu.wanandroid.bean.ProjectTab;
import com.manu.wanandroid.ui.project.fragment.ProjectChildFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @Desc: ProjectChildFragmentPageAdapter
 * @Author: jzman
 * @Date: 2019/5/24 0024 11:38
 */
public class ProjectChildFragmentPageAdapter extends FragmentStatePagerAdapter {
    private List<ProjectTab> mTabList;

    public ProjectChildFragmentPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ProjectChildFragmentPageAdapter(@NonNull FragmentManager fm, List<ProjectTab> tabList) {
        super(fm);
        mTabList = tabList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ProjectChildFragment.newInstance(mTabList.get(position).getId());
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position).getName();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}

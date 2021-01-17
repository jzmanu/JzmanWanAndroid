package com.manu.wanandroid.di.module.fragment;

import com.manu.wanandroid.di.scope.PreFragment;
import com.manu.wanandroid.bean.KsBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @Desc: KsRightFragmentModule
 * @Author: jzman
 * @Date: 2019/6/4 0004 10:45
 */
@Module
public class KsRightFragmentModule {

    @PreFragment
    @Provides
    List<KsBean.ChildrenBean> providerKsRightTabList() {
        return new ArrayList<>();
    }
}

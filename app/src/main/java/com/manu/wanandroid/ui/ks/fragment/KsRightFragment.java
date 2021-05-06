package com.manu.wanandroid.ui.ks.fragment;

import android.view.View;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.android.material.tabs.TabLayout;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.common.Config;
import com.manu.wanandroid.contract.ks.KsContract;
import com.manu.wanandroid.databinding.FragmentKsRightBinding;
import com.manu.wanandroid.di.module.fragment.KsRightFragmentModule;
import com.manu.wanandroid.ui.ks.adapter.KsRightChildPageAdapter;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.ui.main.fragment.KsFragment;
import com.manu.wanandroid.utils.L;

import java.util.List;

import javax.inject.Inject;

/**
 * @Desc: KsRightFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:55
 */
public class KsRightFragment extends BaseLoadMvpFragment<KsContract.CategoryArticlePresenter> implements
        KsFragment.OnKsCategoryInfoListener {

    private static final String TAG = KsRightFragment.class.getSimpleName();

    private FragmentKsRightBinding binding;

    @Inject
    List<Knowledge.ChildrenBean> mKsChildrenBeans;

    private MainActivity mActivity;
    private SkeletonScreen mSkeletonScreenRight;

    @Override
    public View onLayout() {
        binding = FragmentKsRightBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onInject() {
        mActivity = (MainActivity) getActivity();
        assert mActivity != null;
        mActivity.mMainActivityComponent
                .getKsRightFragmentComponent(new KsRightFragmentModule())
                .injectKsFragment(this);
    }

    @Override
    protected KsContract.CategoryArticlePresenter onPresenter() {
        return null;
    }

    @Override
    public void onData() {
        L.i(TAG, "onInitData");

        mActivity.mKsFragment.setOnKsCategoryInfoListener(this);
        binding.tlKsRight.setTabMode(TabLayout.MODE_SCROLLABLE);
        binding.tlKsRight.setupWithViewPager(binding.vpKsRight);

        mSkeletonScreenRight = Skeleton.bind(binding.normalView)
                .load(R.layout.fragment_ks_right_skeleton)
                .color(R.color.colorAnimator)
                .duration(Config.skeletonDuration)
                .show();
    }

    @Override
    public void onKsCategoryInfo(Knowledge ksBean) {
        L.i(TAG, "onKsCategoryInfo->" + (ksBean != null));
        mSkeletonScreenRight.hide();
        if (ksBean != null) {
            mKsChildrenBeans = ksBean.getChildren();
            if (mKsChildrenBeans.size() > 0) {
                binding.tlKsRight.removeAllTabs();
                for (Knowledge.ChildrenBean bean : mKsChildrenBeans) {
                    binding.tlKsRight.addTab(binding.tlKsRight.newTab()
                            .setText(bean.getName())
                            .setTag(bean.getId()));
                }
                binding.vpKsRight.setAdapter(new KsRightChildPageAdapter(getChildFragmentManager(), mKsChildrenBeans));
            }
        }
    }
}

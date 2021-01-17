package com.manu.wanandroid.ui.ks.fragment;

import android.widget.LinearLayout;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.android.material.tabs.TabLayout;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.contract.ks.KsContract;
import com.manu.wanandroid.di.module.fragment.KsRightFragmentModule;
import com.manu.wanandroid.ui.ks.adapter.KsRightChildPageAdapter;
import com.manu.wanandroid.bean.KsBean;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.L;

import java.util.List;

import javax.inject.Inject;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @Desc: KsRightFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:55
 */
public class KsRightFragment extends BaseLoadMvpFragment<KsContract.CategoryArticlePresenter> implements
        KsFragment.OnKsCategoryInfoListener {

    private static final String TAG = KsRightFragment.class.getSimpleName();

    @BindView(R.id.tl_ks_right)
    TabLayout tlKsRight;
    @BindView(R.id.vp_ks_right)
    ViewPager vpKsRight;

    @Inject
    List<KsBean.ChildrenBean> mKsChildrenBeans;
    @BindView(R.id.normal_view)
    LinearLayout normalView;

    private MainActivity mActivity;
    private SkeletonScreen mSkeletonScreenRight;

    @Override
    public int onLayoutId() {
        return R.layout.fragment_ks_right;
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
    public void onInitData() {
        L.i(TAG, "onInitData");

        mActivity.mKsFragment.setOnKsCategoryInfoListener(this);
        tlKsRight.setTabMode(TabLayout.MODE_SCROLLABLE);
        tlKsRight.setupWithViewPager(vpKsRight);

        mSkeletonScreenRight = Skeleton.bind(normalView)
                .load(R.layout.fragment_ks_right_skeleton)
                .color(R.color.colorAnimator)
                .duration(1500)
                .show();
    }

    @Override
    public void onKsCategoryInfo(KsBean ksBean) {
        L.i(TAG, "onKsCategoryInfo->" + (ksBean != null));
        mSkeletonScreenRight.hide();
        if (ksBean != null) {
            mKsChildrenBeans = ksBean.getChildren();
            if (mKsChildrenBeans.size() > 0) {
                tlKsRight.removeAllTabs();
                for (KsBean.ChildrenBean bean : mKsChildrenBeans) {
                    tlKsRight.addTab(tlKsRight.newTab()
                            .setText(bean.getName())
                            .setTag(bean.getId()));
                }
                vpKsRight.setAdapter(new KsRightChildPageAdapter(getChildFragmentManager(), mKsChildrenBeans));
            }
        }
    }
}

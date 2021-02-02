package com.manu.wanandroid.ui.ks.fragment;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.manu.wanandroid.R;
import com.manu.wanandroid.base.adapter.OnRecycleItemClickListener;
import com.manu.wanandroid.base.fragment.BaseLoadMvpFragment;
import com.manu.wanandroid.bean.Knowledge;
import com.manu.wanandroid.common.Config;
import com.manu.wanandroid.contract.ks.KsContract;
import com.manu.wanandroid.databinding.FragmentKsBinding;
import com.manu.wanandroid.di.module.fragment.KsFragmentModule;
import com.manu.wanandroid.presenter.ks.KsCategoryDataPresenter;
import com.manu.wanandroid.ui.ks.adapter.KsCategoryAdapter;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.L;

import java.util.List;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Desc: HomeFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:55
 */
public class KsFragment extends BaseLoadMvpFragment<KsContract.CategoryPresenter> implements KsContract.CategoryView {
    private static final String TAG = KsFragment.class.getSimpleName();

    private FragmentKsBinding binding;

    @Inject
    KsCategoryAdapter mKsCategoryAdapter;
    @Inject
    KsCategoryDataPresenter mKsCategoryDataPresenter;
    @Inject
    KsRightFragment mKsRightFragment;

    private MainActivity mActivity;
    private OnKsCategoryInfoListener mOnKsCategoryInfoListener;
    private int mCurrentClickPosition;
    private SkeletonScreen mSkeletonScreenLeft;

    void setOnKsCategoryInfoListener(OnKsCategoryInfoListener mOnKsCategoryInfoListener) {
        this.mOnKsCategoryInfoListener = mOnKsCategoryInfoListener;
    }

    @Override
    public View onLayout() {
        binding = FragmentKsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onInject() {
        mActivity = (MainActivity) getActivity();
        assert mActivity != null;
        mActivity.mMainActivityComponent
                .getKsFragmentComponent(new KsFragmentModule())
                .injectKsFragment(this);
    }

    @Override
    public void onInitData() {
        L.i(TAG, "onInitData");
        FragmentManager fm = getFragmentManager();
        if (!mKsRightFragment.isAdded()) {
            assert fm != null;
            fm.beginTransaction().add(R.id.fl_ks_right_container, mKsRightFragment).commit();
        }

        binding.rvKsLeft.setLayoutManager(new LinearLayoutManager(mActivity));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.drawable.ks_category_rv_divider_bg);
        assert drawable != null;
        itemDecoration.setDrawable(drawable);
//        rvKsLeft.addItemDecoration(itemDecoration);

        mSkeletonScreenLeft = Skeleton.bind(binding.rvKsLeft)
                .adapter(mKsCategoryAdapter)
                .load(R.layout.recycle_ks_item_first_category_skeleton)
                .color(R.color.colorAnimator)
                .duration(Config.INSTANCE.getSkeletonDuration())
                .show();


        binding.rvKsLeft.addOnItemTouchListener(new OnRecycleItemClickListener(binding.rvKsLeft) {
            @Override
            public void onRecycleItemClick(View view, int position, RecyclerView.ViewHolder holder) {
                if (mCurrentClickPosition == position) return;
                mCurrentClickPosition = position;
                Knowledge bean = mKsCategoryAdapter.getItem(holder.getAdapterPosition());
                mKsCategoryAdapter.setSelectPosition(holder.getAdapterPosition());
                if (mOnKsCategoryInfoListener != null)
                    mOnKsCategoryInfoListener.onKsCategoryInfo(bean);
            }
        });
        mKsCategoryDataPresenter.getKsCategoryData();
    }

    @Override
    public void onKsCategoryDataSuccess(List<Knowledge> result) {
        L.i(TAG, "onKsCategoryDataSuccess" + result);
        onShowNormalContent();
        mSkeletonScreenLeft.hide();
        mKsCategoryAdapter.addAll(result);

        if (mOnKsCategoryInfoListener != null) {
            mOnKsCategoryInfoListener.onKsCategoryInfo(result.get(0));
            mKsCategoryAdapter.setSelectPosition(0);
        }
    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        mSkeletonScreenLeft.hide();
    }

    @Override
    protected KsContract.CategoryPresenter onPresenter() {
        return mKsCategoryDataPresenter;
    }

    public interface OnKsCategoryInfoListener {
        void onKsCategoryInfo(Knowledge ksBean);
    }
}

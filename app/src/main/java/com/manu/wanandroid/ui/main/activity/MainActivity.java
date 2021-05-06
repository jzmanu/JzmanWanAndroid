package com.manu.wanandroid.ui.main.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.manu.wanandroid.R;
import com.manu.wanandroid.app.App;
import com.manu.wanandroid.base.activity.BaseMvpActivity;
import com.manu.wanandroid.bean.Integral;
import com.manu.wanandroid.bean.IntegralInfo;
import com.manu.wanandroid.common.Account;
import com.manu.wanandroid.common.AppStatusTrack;
import com.manu.wanandroid.common.Common;
import com.manu.wanandroid.contract.home.IntegralContract;
import com.manu.wanandroid.databinding.ActivityMainBinding;
import com.manu.wanandroid.di.component.activity.DaggerMainActivityComponent;
import com.manu.wanandroid.di.component.activity.MainActivityComponent;
import com.manu.wanandroid.presenter.home.MineIntegralPresenter;
import com.manu.wanandroid.ui.home.activity.AboutActivity;
import com.manu.wanandroid.ui.home.activity.MineCollectActivity;
import com.manu.wanandroid.ui.home.activity.MineIntegralActivity;
import com.manu.wanandroid.ui.home.activity.MineShareActivity;
import com.manu.wanandroid.ui.home.activity.ReadHistoryActivity;
import com.manu.wanandroid.ui.main.fragment.MineFragment;
import com.manu.wanandroid.ui.search.activity.SearchActivity;
import com.manu.wanandroid.ui.home.activity.SettingActivity;
import com.manu.wanandroid.ui.main.fragment.HomeFragment;
import com.manu.wanandroid.ui.main.fragment.KsFragment;
import com.manu.wanandroid.ui.main.adapter.MainPagerAdapter;
import com.manu.wanandroid.ui.main.fragment.ProjectFragment;
import com.manu.wanandroid.utils.BitmapUtil;
import com.manu.wanandroid.utils.L;
import com.manu.wanandroid.utils.SharePreferenceHelperKt;
import com.manu.wanandroid.utils.StatusBarUtil;
import com.manu.wanandroid.utils.Util;
import com.manu.wanandroid.widget.PortraitView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @Desc: MainActivity
 * @Author: jzman
 */
public class MainActivity extends BaseMvpActivity<IntegralContract.Presenter> implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener, NavigationView.OnNavigationItemSelectedListener, IntegralContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    HomeFragment mHomeFragment;
    @Inject
    ProjectFragment mProjectFragment;
    @Inject
    public KsFragment mKsFragment;
    @Inject
    List<Fragment> mFragments;
    @Inject
    MineIntegralPresenter mMineIntegralPresenter;

    private TextView tvName, tvLevel, tvRank;
    private PortraitView portraitView;
    public MainActivityComponent mMainActivityComponent;
    private ActivityMainBinding binding;
    private int mDrawItemId;

    @Override
    public View onLayout() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onInject() {
        App mApplication = (App) getApplication();
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .build();
        mMainActivityComponent.injectMainActivity(this);
    }

    @Override
    public void onToolbar() {
        super.onToolbar();
        setSupportActionBar(binding.toolBarInclude.toolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        binding.dlMain.setStatusBarBackgroundColor(getResources().getColor(R.color.light_transparent));
        actionBar.setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, binding.dlMain, binding.toolBarInclude.toolBar, R.string.common_open, R.string.common_close);
        toggle.syncState();

        View headerView = binding.nvMain.getHeaderView(0);
        tvName = headerView.findViewById(R.id.tvName);
        tvLevel = headerView.findViewById(R.id.tvLevel);
        tvRank = headerView.findViewById(R.id.tvRank);
        portraitView = headerView.findViewById(R.id.portraitView);
        Bitmap bitmap = BitmapUtil.INSTANCE.decodeSampleFromBitmap(this,R.drawable.default_portrait,
                90, 90, Util.INSTANCE.getDensity(this));
        portraitView.setImageBitmap(bitmap);

        binding.dlMain.addDrawerListener(toggle);
        binding.dlMain.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                if (Account.INSTANCE.isLogin()) {
                    if (mDrawItemId == R.id.nv_share) {
                        startActivity(MainActivity.this, MineShareActivity.class);
                    } else if (mDrawItemId == R.id.nv_collect) {
                        startActivity(MainActivity.this, MineCollectActivity.class);
                    } else if (mDrawItemId == R.id.nv_integral) {
                        startActivity(MainActivity.this, MineIntegralActivity.class);
                    }
                } else {
                    if (mDrawItemId == R.id.nv_share || mDrawItemId == R.id.nv_collect ||
                            mDrawItemId == R.id.nv_integral) {
                        startLoginActivityForResult();
                    }
                }

                if (mDrawItemId == R.id.nv_history) {
                    startActivity(MainActivity.this, ReadHistoryActivity.class);
                } else if (mDrawItemId == R.id.nv_setting) {
                    startActivity(MainActivity.this, SettingActivity.class);
                } else if (mDrawItemId == R.id.nv_about) {
                    startActivity(MainActivity.this, AboutActivity.class);
                }
            }
        });
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
    }


    @Override
    public void onData() {
        mFragments.add(mHomeFragment);
        mFragments.add(mProjectFragment);
        mFragments.add(mKsFragment);
        binding.bnvMain.setOnNavigationItemSelectedListener(this);
        binding.nvMain.setNavigationItemSelectedListener(this);
        binding.vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), mFragments));
        binding.vpMain.addOnPageChangeListener(this);
        binding.vpMain.setOffscreenPageLimit(2);

        tvName.setOnClickListener(v -> {
            if (!Account.INSTANCE.isLogin()) {
                startLoginActivityForResult();
            }
        });

        if (Account.INSTANCE.isLogin()) {
            mMineIntegralPresenter.getMineIntegralInfo();
        } else {
            tvName.setText(R.string.nv_head_please_login);
            String level = String.format(getString(R.string.nv_head_level), "-");
            String rank = String.format(getString(R.string.nv_head_rank), "-");
            tvLevel.setText(level);
            tvRank.setText(rank);
        }

        binding.toolBarInclude.ivSearch.setVisibility(View.VISIBLE);
        binding.toolBarInclude.ivSearch.setOnClickListener(v -> startActivity(MainActivity.this, SearchActivity.class));
    }

    @Override
    protected IntegralContract.Presenter onPresenter() {
        return mMineIntegralPresenter;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String action = intent.getStringExtra(AppStatusTrack.ACTION_HOME);
        if (AppStatusTrack.STATUS_FOCUS_KILLED.equals(action)) {
            onProtectApp();
        }

        String event = intent.getStringExtra(Account.LOGOUT);
        if (Account.LOGOUT.equals(event)) {
            AgentActivity.startLoginActivity(this);
            finish();
        }
    }

    @Override
    protected void onProtectApp() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra(AppStatusTrack.ACTION_HOME, AppStatusTrack.STATUS_FOCUS_KILLED);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawItemId = menuItem.getItemId();
        if (mDrawItemId == R.id.bnv_home) {
            binding.vpMain.setCurrentItem(0);
        } else if (mDrawItemId == R.id.bnv_project) {
            binding.vpMain.setCurrentItem(1);
        } else if (mDrawItemId == R.id.bnv_ks) {
            binding.vpMain.setCurrentItem(2);
        }else {
            binding.dlMain.close();
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                binding.bnvMain.setSelectedItemId(R.id.bnv_home);
                break;
            case 1:
                binding.bnvMain.setSelectedItemId(R.id.bnv_project);
                break;
            case 2:
                binding.bnvMain.setSelectedItemId(R.id.bnv_ks);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onGetMineIntegralSuccess(@NotNull List<Integral> result) {
    }

    @Override
    public void onGetMineIntegralInfoSuccess(@NotNull IntegralInfo result) {
        L.i(TAG, "onGetMineIntegralInfoSuccess:" + result);
        tvName.setText(Account.INSTANCE.getUser().getNickname());
        String level = String.format(getString(R.string.nv_head_level), String.valueOf(result.getLevel()));
        String rank = String.format(getString(R.string.nv_head_rank), String.valueOf(result.getRank()));
        tvLevel.setText(level);
        tvRank.setText(rank);
        String integralInfo = Common.INSTANCE.getGson().toJson(result);
        SharePreferenceHelperKt.putSpValue(Account.INTEGRAL_INFO, integralInfo);
    }

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private void startLoginActivityForResult() {
        AgentActivity.startLoginActivityForResult(this, result -> {
            mMineIntegralPresenter.getMineIntegralInfo();
        });
    }
}

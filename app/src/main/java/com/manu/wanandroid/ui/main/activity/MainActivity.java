package com.manu.wanandroid.ui.main.activity;


import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.manu.wanandroid.R;
import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.base.activity.BaseActivity;
import com.manu.wanandroid.common.AppStatusTrack;
import com.manu.wanandroid.common.SplashActivity;
import com.manu.wanandroid.databinding.ActivityMainBinding;
import com.manu.wanandroid.di.component.activity.DaggerMainActivityComponent;
import com.manu.wanandroid.di.component.activity.MainActivityComponent;
import com.manu.wanandroid.ui.home.activity.AboutActivity;
import com.manu.wanandroid.ui.home.activity.MineCollectActivity;
import com.manu.wanandroid.ui.home.activity.MineIntegralActivity;
import com.manu.wanandroid.ui.home.activity.MineShareActivity;
import com.manu.wanandroid.ui.home.activity.ReadHistoryActivity;
import com.manu.wanandroid.ui.home.activity.SystemSettingActivity;
import com.manu.wanandroid.ui.home.fragment.HomeFragment;
import com.manu.wanandroid.ui.ks.fragment.KsFragment;
import com.manu.wanandroid.ui.main.adapter.MainPagerAdapter;
import com.manu.wanandroid.ui.project.fragment.ProjectFragment;
import com.manu.wanandroid.utils.StatusBarUtil;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @Desc: MainActivity
 * @Author: jzman
 * @Date: 2019/5/8 0008 9:38
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener,NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    HomeFragment mHomeFragment;
    @Inject
    ProjectFragment mProjectFragment;
    @Inject
    public KsFragment mKsFragment;
    @Inject
    List<Fragment> mFragments;

    public MainActivityComponent mMainActivityComponent;
    private ActivityMainBinding binding;

    @Override
    public View onLayout() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onInject() {
        MApplication mApplication = (MApplication) getApplication();
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .build();
        mMainActivityComponent.injectMainActivity(this);
    }

    @Override
    public void onInitToolbar() {
        super.onInitToolbar();
        setSupportActionBar(binding.toolBarInclude.toolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        binding.dlMain.setStatusBarBackgroundColor(getResources().getColor(R.color.light_transparent));
        actionBar.setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, binding.dlMain, binding.toolBarInclude.toolBar, R.string.common_open, R.string.common_close);
        toggle.syncState();
        binding.dlMain.addDrawerListener(toggle);

        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
    }


    @Override
    public void onInitData() {
        mFragments.add(mHomeFragment);
        mFragments.add(mProjectFragment);
        mFragments.add(mKsFragment);
        binding.bnvMain.setOnNavigationItemSelectedListener(this);
        binding.nvMain.setNavigationItemSelectedListener(this);
        binding.vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), mFragments));
        binding.vpMain.addOnPageChangeListener(this);
        binding.vpMain.setOffscreenPageLimit(2);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String action = intent.getStringExtra(AppStatusTrack.ACTION_HOME);
        if (AppStatusTrack.STATUS_FOCUS_KILLED.equals(action)) {
            onProtectApp();
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
        int itemId = menuItem.getItemId();
        if (itemId == R.id.bnv_home) {
            binding.vpMain.setCurrentItem(0);
        } else if (itemId == R.id.bnv_project) {
            binding.vpMain.setCurrentItem(1);
        } else if (itemId == R.id.bnv_ks) {
            binding.vpMain.setCurrentItem(2);
        }else if(itemId == R.id.nv_share){
            MineShareActivity.startMineShareActivity(this);
            binding.dlMain.close();
        }else if(itemId == R.id.nv_collect){
            MineCollectActivity.startMineCollectActivity(this);
            binding.dlMain.close();
        }else if(itemId == R.id.nv_integral){
            MineIntegralActivity.startMineIntegralActivity(this);
            binding.dlMain.close();
        }else if(itemId == R.id.nv_history){
            ReadHistoryActivity.startReadHistoryActivity(this);
            binding.dlMain.close();
        }else if(itemId == R.id.nv_setting){
            SystemSettingActivity.startSystemSettingActivity(this);
            binding.dlMain.close();
        }else if(itemId == R.id.nv_about){
            AboutActivity.startMineAboutActivity(this);
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

    public static void startMainActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}

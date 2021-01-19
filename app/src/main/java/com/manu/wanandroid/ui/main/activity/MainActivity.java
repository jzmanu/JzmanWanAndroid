package com.manu.wanandroid.ui.main.activity;


import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.manu.wanandroid.R;
import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.base.activity.BaseActivity;
import com.manu.wanandroid.common.Account;
import com.manu.wanandroid.common.AppStatusTrack;
import com.manu.wanandroid.common.SplashActivity;
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
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @Desc: MainActivity
 * @Author: jzman
 * @Date: 2019/5/8 0008 9:38
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener,NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.bnv_main)
    BottomNavigationView bnvMain;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;

    @Inject
    HomeFragment mHomeFragment;
    @Inject
    ProjectFragment mProjectFragment;
    @Inject
    public KsFragment mKsFragment;
    @Inject
    List<Fragment> mFragments;

    public MainActivityComponent mMainActivityComponent;

    @Override
    public int onLayoutId() {
        return R.layout.activity_main;
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
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        dlMain.setStatusBarBackgroundColor(getResources().getColor(R.color.light_transparent));
        actionBar.setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, dlMain, toolBar, R.string.common_open, R.string.common_close);
        toggle.syncState();
        dlMain.addDrawerListener(toggle);

        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
    }


    @Override
    public void onInitData() {
        mFragments.add(mHomeFragment);
        mFragments.add(mProjectFragment);
        mFragments.add(mKsFragment);
        bnvMain.setOnNavigationItemSelectedListener(this);
        nvMain.setNavigationItemSelectedListener(this);
        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), mFragments));
        vpMain.addOnPageChangeListener(this);
        vpMain.setOffscreenPageLimit(2);

        if (Account.INSTANCE.isLogin()){

        }
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
            vpMain.setCurrentItem(0);
        } else if (itemId == R.id.bnv_project) {
            vpMain.setCurrentItem(1);
        } else if (itemId == R.id.bnv_ks) {
            vpMain.setCurrentItem(2);
        }else if(itemId == R.id.nv_share){
            MineShareActivity.Companion.startMineShareActivity(this);
        }else if(itemId == R.id.nv_collect){
            MineCollectActivity.Companion.startMineCollectActivity(this);
        }else if(itemId == R.id.nv_integral){
            MineIntegralActivity.Companion.startMineIntegralActivity(this);
        }else if(itemId == R.id.nv_history){
            ReadHistoryActivity.Companion.startReadHistoryActivity(this);
        }else if(itemId == R.id.nv_setting){
            SystemSettingActivity.Companion.startSystemSettingActivity(this);
        }else if(itemId == R.id.nv_about){
            AboutActivity.Companion.startAboutActivity(this);
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
                bnvMain.setSelectedItemId(R.id.bnv_home);
                break;
            case 1:
                bnvMain.setSelectedItemId(R.id.bnv_project);
                break;
            case 2:
                bnvMain.setSelectedItemId(R.id.bnv_ks);
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

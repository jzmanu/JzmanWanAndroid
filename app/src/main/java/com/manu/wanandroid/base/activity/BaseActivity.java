package com.manu.wanandroid.base.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.common.AppStatusTrack;


/**
 * @Desc: BaseActivity
 * @Author: jzman
 * @Date: 2019/5/7 0007 16:30
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private Unbinder unbinder;

    public abstract int onLayoutId();

    public abstract void onInitData();

    public void onInitMessageView() {
    }

    public abstract void onInject();

    public void onInitToolbar() {
    }

    public void onAttach() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayoutId());
        unbinder = ButterKnife.bind(this);
        if (AppStatusTrack.getInstance().getAppStatus() == -1) {
            onProtectApp();
        } else {
            onAttach();
            onInject();
            onInitMessageView();
            onInitToolbar();
            onInitData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void onProtectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppStatusTrack.ACTION_HOME, AppStatusTrack.STATUS_FOCUS_KILLED);
        startActivity(intent);
    }
}

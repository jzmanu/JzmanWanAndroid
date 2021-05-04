package com.manu.wanandroid.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.manu.wanandroid.common.AppStatusTrack;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.KeyBoardUtil;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @Desc: BaseActivity
 * @Author: jzman
 * @Date: 2019/5/7 0007 16:30
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public abstract View onLayout();

    public abstract void onData();

    public void onMessageView() {
    }

    public abstract void onInject();

    public void onToolbar() {
    }

    public void onAttach() {
    }

    public void onClick(View v, @IdRes int id) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        if (AppStatusTrack.getInstance().getAppStatus() == -1) {
            onProtectApp();
        } else {
            onInject();
            onAttach();
            onMessageView();
            onToolbar();
            onData();
        }
    }

    @Override
    public void onClick(View v) {
        onClick(v, v.getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = this.getCurrentFocus();
            KeyBoardUtil.INSTANCE.hideKeyboard(ev, view, this);
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void onProtectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppStatusTrack.ACTION_HOME, AppStatusTrack.STATUS_FOCUS_KILLED);
        startActivity(intent);
    }

    protected <Activity> void startActivity(Context context, Class<Activity> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    protected void toast(String message) {
        if (message == null) return;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(@StringRes int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }
}

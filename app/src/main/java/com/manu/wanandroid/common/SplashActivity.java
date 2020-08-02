package com.manu.wanandroid.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manu.wanandroid.R;
import com.manu.wanandroid.ui.main.activity.AgentActivity;
import com.manu.wanandroid.ui.main.activity.AgentActivity2;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.StatusBarUtil;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.android.FlutterFragment;

/**
 * @Desc: SplashActivity
 * @Author: jzman
 * @Date: 2019/5/8 0008 9:25
 */
public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.tvData)
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusTrack.getInstance().setAppStatus(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
        Handler mHandler = new Handler(Looper.myLooper());

        mHandler.postDelayed(() -> {
            Intent intent = AgentActivity
                    .withNewEngine()
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
                    .build(SplashActivity.this);

//            Intent intent = AgentActivity.createDefaultIntent(this);
            startActivity(intent);
            finish();
        },2000);
    }

}

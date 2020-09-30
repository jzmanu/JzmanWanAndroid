package com.manu.wanandroid.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.manu.wanandroid.R;
import com.manu.wanandroid.ui.main.activity.AgentActivity;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.SharePreferenceHelperKt;
import com.manu.wanandroid.utils.StatusBarUtil;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;

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

        long expires = SharePreferenceHelperKt.getSpValue(Common.COOKIE_EXPIRES,0L);
        if (new Date(expires).before(new Date())){
            mHandler.postDelayed(() -> {
                Intent intent = AgentActivity
                        .withNewEngine()
                        .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
                        .build(SplashActivity.this);
                startActivity(intent);
                finish();
            },2000);
        }else{
            MainActivity.startMainActivity(this);
            finish();
        }
    }
}

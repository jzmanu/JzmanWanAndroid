package com.manu.wanandroid.common;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.manu.wanandroid.R;
import com.manu.wanandroid.ui.main.activity.AgentActivity;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

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

        if (Config.INSTANCE.getConfigStartLoginAuth()){
            if (Account.INSTANCE.isLogin()){
                MainActivity.startMainActivity(this);
                finish();
            }else{
                mHandler.postDelayed(() -> {
                    AgentActivity.startLoginActivity(this);
                    finish();
                },1500);
            }
        }else{
            MainActivity.startMainActivity(this);
            finish();
        }
    }
}

package com.manu.wanandroid.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.manu.wanandroid.R;
import com.manu.wanandroid.ui.main.activity.MainActivity;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Desc: SplashActivity
 * @Author: jzman
 * @Date: 2019/5/8 0008 9:25
 */
public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.tvData)
    TextView tvData;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusTrack.getInstance().setAppStatus(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mHandler = new MHandler(this);
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(0);
    }

    private static class MHandler extends Handler {
        private WeakReference<SplashActivity> weakReference;

        MHandler(SplashActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                SplashActivity activity = weakReference.get();
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        }
    }
}

package com.manu.wanandroid.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.manu.wanandroid.R;
import com.manu.wanandroid.ui.main.activity.AgentActivity;
import com.manu.wanandroid.ui.main.activity.MainActivity;
import com.manu.wanandroid.utils.StatusBarUtil;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import io.flutter.embedding.android.FlutterActivity;

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
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
        mHandler = new Handler(Looper.myLooper());
        mHandler.postDelayed(() -> {
            Intent intent = AgentActivity
                    .withNewEngine()
                    .backgroundMode(FlutterActivity.BackgroundMode.transparent)
                    .build(SplashActivity.this);
            startActivity(intent);
            finish();
        },2000);


    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(0);
    }



//    public static void main(String[] args) {
//        String data = "http://www.baidu.com/jyDataCollect/dataCollect/a.gif?ts=2561s&t=timing&sid=kc1hqledmfg&uo=%7B%22uo%22%3A%7B%22uid%22%3A%22testjiulian%22%2C%22step%22%3A10%2C%22sid%22%3A%22004003FF0003255000028CBA2515FB32%22%2C%22ti%22%3A%22%E8%B4%B5%E5%B7%9E%E5%B9%BF%E7%94%B5%E6%96%B0%E5%AA%92%E4%BD%93%22%2C%22pid%22%3A%221%22%7D%2C%22bs%22%3A%7B%22us%22%3A1%2C%22ver%22%3A%222.0%22%2C%22sid%22%3A%22MGV2000-KL_GZ%22%2C%22ac%22%3A%221%22%2C%22en%22%3A%22default%22%2C%22gid%22%3A%221%22%2C%22epf%22%3A%221%22%2C%22operators%22%3A%22%E7%A7%BB%E5%8A%A8%22%7D%7D&sendtime=600000";
//        try {
//            String deurl = URLDecoder.decode(data,"UTF-8");
//            System.out.println(deurl);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
}

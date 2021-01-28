package com.manu.wanandroid.common

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.manu.wanandroid.databinding.ActivitySplashBinding
import com.manu.wanandroid.ui.main.activity.AgentActivity
import com.manu.wanandroid.ui.main.activity.MainActivity
import com.manu.wanandroid.utils.StatusBarUtil

/**
 * @Desc: SplashActivity
 * @Author: jzman
 * @Date: 2019/5/8 0008 9:25
 */
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppStatusTrack.getInstance().appStatus = 0
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)
        val mHandler = Handler(Looper.myLooper())
        if (Config.configStartLoginAuth) {
            if (Account.isLogin()) {
                MainActivity.startMainActivity(this)
                finish()
            } else {
                mHandler.postDelayed({
                    AgentActivity.startLoginActivity(this)
                    finish()
                }, 1500)
            }
        } else {
            MainActivity.startMainActivity(this)
            finish()
        }
    }
}
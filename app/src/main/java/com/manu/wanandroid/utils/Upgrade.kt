package com.manu.wanandroid.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.manu.wanandroid.R
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.beta.UpgradeInfo
import com.tencent.bugly.beta.ui.BetaActivity
import com.tencent.bugly.beta.ui.UILifecycleListener


/**
 * @Desc: App升级配置类
 * @Author: jzman
 */
object Upgrade {
    private const val TAG = "Upgrade"
    fun initUpgradeConfig(){
        /**
         * 升级弹窗的布局文件
         */
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog

        /**
         * 设置wifi下自动下载
         */
        Beta.autoDownloadOnWifi = false

        /**
         * 是否显示要升级的apk的信息
         */
        Beta.canShowApkInfo = true

        /**
         * 是否显示消息通知
         */
        Beta.enableNotification = false

        Beta.showInterruptedStrategy = true;

        Beta.upgradeDialogLifecycleListener = object : UILifecycleListener<UpgradeInfo?> {
            override fun onCreate(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                L.i(TAG, "onCreate")
                // 解决弹窗时状态栏为黑色的问题.
                if (context is BetaActivity) {
                    val betaActivity:BetaActivity = context
                    val window = betaActivity.window
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }

            override fun onStart(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                L.i(TAG, "onStart")
            }

            override fun onResume(context: Context?, view: View, upgradeInfo: UpgradeInfo?) {
                L.i(TAG, "onResume")
                view.findViewById<Button>(R.id.upgradeCancel).setOnClickListener {
                    if (context is BetaActivity) {
                        context.finish()
                    }
                }
            }

            override fun onPause(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                L.i(TAG, "onPause")
            }

            override fun onStop(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                L.i(TAG, "onStop")
            }

            override fun onDestroy(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                L.i(TAG, "onDestory")
            }
        }
    }
}
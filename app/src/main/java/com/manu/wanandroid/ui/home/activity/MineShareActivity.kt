package com.manu.wanandroid.ui.home.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import com.manu.wanandroid.R
import com.manu.wanandroid.base.activity.BaseActivity
import com.manu.wanandroid.databinding.ActivityMineShareBinding
import com.manu.wanandroid.utils.StatusBarUtil

/**
 * @Desc: 我的分享
 * @Author: jzman
 */
class MineShareActivity : BaseActivity() {
    private lateinit var binding: ActivityMineShareBinding
    override fun onLayout(): View {
        binding = ActivityMineShareBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {}

    override fun onInitToolbar() {
        super.onInitToolbar()
        setSupportActionBar(binding.toolBarInclude.toolBar)
        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)
    }

    override fun onInitData() {
        binding.toolBarInclude.tvCenterTitle.setText(R.string.nv_share)
    }

    companion object {
        @JvmStatic
        fun startMineShareActivity(context: Activity) {
            val intent = Intent(context, MineShareActivity::class.java)
            context.startActivity(intent)
        }
    }
}
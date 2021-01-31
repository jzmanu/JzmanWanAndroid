package com.manu.wanandroid.ui.home.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import com.manu.wanandroid.R
import com.manu.wanandroid.base.activity.BaseActivity
import com.manu.wanandroid.databinding.ActivityReadHistoryBinding
import com.manu.wanandroid.utils.StatusBarUtil

/**
 * @Desc: 阅读历史
 * @Author: jzman
 */
class ReadHistoryActivity : BaseActivity() {
    private lateinit var binding: ActivityReadHistoryBinding
    override fun onLayout(): View {
        binding = ActivityReadHistoryBinding.inflate(layoutInflater)
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
        binding.toolBarInclude.tvCenterTitle.setText(R.string.nv_history)
    }

    companion object {
        @JvmStatic
        fun startReadHistoryActivity(context: Activity) {
            val intent = Intent(context, ReadHistoryActivity::class.java)
            context.startActivity(intent)
        }
    }
}
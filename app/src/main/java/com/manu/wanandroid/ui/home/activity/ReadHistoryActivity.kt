package com.manu.wanandroid.ui.home.activity

import android.content.Context
import android.content.Intent
import android.view.MenuItem
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

    companion object{
        fun startReadHistoryActivity(context: Context){
            val intent = Intent(context,ReadHistoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityReadHistoryBinding;

    override fun onLayout(): View {
        binding = ActivityReadHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInitToolbar() {
        super.onInitToolbar()
        setSupportActionBar(binding.toolBarInclude.toolBar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)
    }

    override fun onInitData() {
        binding.toolBarInclude.tvCenterTitle.text = getString(R.string.nv_history)
    }

    override fun onInject() {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return false
    }
}
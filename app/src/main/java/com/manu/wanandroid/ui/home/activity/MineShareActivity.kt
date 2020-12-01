package com.manu.wanandroid.ui.home.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.manu.wanandroid.R
import com.manu.wanandroid.base.activity.BaseActivity
import com.manu.wanandroid.utils.StatusBarUtil

/**
 * @Desc: 我的分享
 * @Author: jzman
 */
class MineShareActivity : BaseActivity() {

    companion object{
        fun startMineShareActivity(context: Context){
            val intent = Intent(context,MineShareActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onLayoutId(): Int {
        return R.layout.activity_mine_share
    }

    override fun onInitToolbar() {
        super.onInitToolbar()
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)
    }

    override fun onInitData() {
        val title = findViewById<TextView>(R.id.tv_center_title)
        title.text = getString(R.string.nv_share)
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
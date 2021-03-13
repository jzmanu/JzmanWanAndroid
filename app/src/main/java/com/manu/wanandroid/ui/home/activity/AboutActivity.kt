package com.manu.wanandroid.ui.home.activity

import android.view.View
import com.manu.wanandroid.R
import com.manu.wanandroid.base.activity.BaseActivity
import com.manu.wanandroid.databinding.ActivityAboutBinding
import com.manu.wanandroid.utils.StatusBarUtil
import com.manu.wanandroid.widget.AboutPage
import com.manu.wanandroid.widget.Element

/**
 * @Desc: 关于
 * @Author: jzman
 */
class AboutActivity : BaseActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onLayout(): View {
        binding = ActivityAboutBinding.inflate(layoutInflater)
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
        binding.toolBarInclude.tvCenterTitle.setText(R.string.nv_about)
        val aboutPage = AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.jzman)
                .setDescription("任尔几路来，我自一路去")
                .addGitHub("jzmanu","Github")
                .addWebsite("https://juejin.cn/user/3526889030301325","掘金",R.drawable.about_juejin)
                .addEmail("jzmanuc@gmail.com","作者邮箱",R.drawable.about_email)
                .addWebsite("https://github.com/jzmanu/JzmanWanAndroid/issues","意见反馈",R.drawable.about_suggest)
                .addWebsite("https://www.wanandroid.com/","致谢")
                .addItem(Element("微信搜索「躬行之」公众号交流学习",R.drawable.about_zan),false)
                .create()
        binding.aboutContainer.addView(aboutPage)
    }
}
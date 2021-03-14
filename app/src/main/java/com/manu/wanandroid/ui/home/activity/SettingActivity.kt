package com.manu.wanandroid.ui.home.activity

import android.content.Intent
import android.view.View
import android.widget.CompoundButton
import com.manu.wanandroid.R
import com.manu.wanandroid.app.App
import com.manu.wanandroid.base.activity.BaseMvpActivity
import com.manu.wanandroid.common.Account
import com.manu.wanandroid.common.Common
import com.manu.wanandroid.contract.account.LoginContract
import com.manu.wanandroid.databinding.ActivitySystemSettingBinding
import com.manu.wanandroid.di.component.activity.DaggerSettingActivityComponent
import com.manu.wanandroid.presenter.account.LoginPresenter
import com.manu.wanandroid.ui.main.activity.MainActivity
import com.manu.wanandroid.utils.L
import com.manu.wanandroid.utils.StatusBarUtil
import com.manu.wanandroid.utils.getSpValue
import com.manu.wanandroid.utils.putSpValue
import com.tencent.bugly.beta.Beta
import javax.inject.Inject

/**
 * @Desc: 设置
 * @Author: jzman
 */
class SettingActivity : BaseMvpActivity<LoginContract.Presenter>(), LoginContract.View,
        CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivitySystemSettingBinding

    @Inject
    lateinit var mLoginPresenter: LoginPresenter
    override fun onLayout(): View {
        binding = ActivitySystemSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {
        val mMApplication = application as App
        DaggerSettingActivityComponent.builder()
                .appComponent(mMApplication.appComponent)
                .build()
                .injectSettingActivity(this)
    }

    override fun onToolbar() {
        super.onToolbar()
        setSupportActionBar(binding.toolBarInclude.toolBar)
        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)

        binding.rlVersion.setOnClickListener(this)
        binding.rlLogout.setOnClickListener(this)
        binding.scLogin.setOnCheckedChangeListener(this)
        binding.scAutoDownload.setOnCheckedChangeListener(this)

        val startLogin = getSpValue(Common.config_start_login, false)
        val autoDownload =  getSpValue(Common.config_auto_download_upgrade, false)
        binding.scLogin.isChecked = startLogin
        binding.scAutoDownload.isChecked = autoDownload
    }

    override fun onData() {
        binding.toolBarInclude.tvCenterTitle.setText(R.string.nv_setting)
    }

    override fun onClick(v: View?, id: Int) {
        when (id) {
            R.id.rlVersion -> {
                Beta.checkAppUpgrade()
            }
            R.id.rlLogout -> {
                if (Account.isLogin()) {
                    mLoginPresenter.logout()
                } else {
                    toast(R.string.account_please_login)
                }
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.scLogin -> {
                putSpValue(Common.config_start_login, isChecked)
            }

            R.id.scAutoDownload -> {
                putSpValue(Common.config_auto_download_upgrade, isChecked)
            }
        }
    }

    override fun onPresenter(): LoginContract.Presenter {
        return mLoginPresenter
    }

    override fun onLogoutSuccess() {
        L.i(TAG, "onLogoutSuccess")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Account.LOGOUT, Account.LOGOUT)
        startActivity(intent)
        finish()
    }

    companion object {
        private val TAG = MineCollectActivity::class.java.simpleName
    }
}
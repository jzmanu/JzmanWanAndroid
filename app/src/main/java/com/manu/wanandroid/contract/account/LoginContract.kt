package com.manu.wanandroid.contract.account

import com.manu.wanandroid.bean.User
import com.manu.wanandroid.base.mvp.presenter.IPresenter
import com.manu.wanandroid.base.mvp.view.IView

/**
 * @Desc: LoginContract
 * @Author: jzman
 * @Date: 2021/1/17 19:43.
 */
interface LoginContract {
    interface View : IView{
        fun onLoginSuccess(user: User)
    }

    interface Presenter :IPresenter<View>{
        fun login(username:String, password:String)
    }
}
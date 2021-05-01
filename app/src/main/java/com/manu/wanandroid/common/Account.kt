package com.manu.wanandroid.common

import com.manu.wanandroid.bean.IntegralInfo
import com.manu.wanandroid.bean.User
import com.manu.wanandroid.utils.getSpValue
import java.util.*

/**
 * @Desc: 帐户信息
 * @Author: Administrator
 * @Date: 2020/8/19 16:29.
 */
object Account {
    /** 登录路径 */
    const val LOGIN = "user/login"
    /** cookie过期时间 */
    const val COOKIE_EXPIRES = "flutter.expires"
    /** 退出登陆 */
    const val LOGOUT = "logout"
    /** 用户信息 */
    const val USER_INFO = "user_info"
    /** 积分等级信息 */
    const val INTEGRAL_INFO = "integral_info"

    /**
     * 是否已经登录，暂时过期也算未登录
     */
    fun isLogin(): Boolean {
        val expires = getSpValue(COOKIE_EXPIRES, 0L)
        return Date(expires).after(Date())
    }

    /**
     * 获取当前用户
     */
    fun getUser() :User{
        val info: String = getSpValue(USER_INFO,"")
        return Common.gson.fromJson(info,User::class.java)
    }

    /**
     * 获取当前用户的积分信息
     */
    fun getIntegralInfo() :IntegralInfo?{
        val info: String = getSpValue(INTEGRAL_INFO,"")
        if (info.isEmpty()) return null
        return Common.gson.fromJson(info,IntegralInfo::class.java)
    }

    /**
     * 从持久化数据中获取一些需要的数据
     */
    fun load() {

    }
}
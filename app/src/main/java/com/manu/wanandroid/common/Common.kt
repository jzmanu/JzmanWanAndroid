package com.manu.wanandroid.common

import com.google.gson.Gson

/**
 * @Desc:
 * @Author: jzman
 */
object Common {
    /** gson */
    var gson:Gson = Gson()
    /** 是否登录  */
    const val config_start_login =  "config_start_login"
    /** 是否自动下载更新 */
    const val config_auto_download_upgrade =  "config_auto_download_upgrade"
}
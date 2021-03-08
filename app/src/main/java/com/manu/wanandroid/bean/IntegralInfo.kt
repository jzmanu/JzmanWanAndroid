package com.manu.wanandroid.bean

/**
 * @Desc: 我的积分信息
 * @Author: jzman
 */
data class IntegralInfo(
    val coinCount: Int,
    val level: Int,
    val nickname: String,
    val rank: String,
    val userId: Int,
    val username: String
)
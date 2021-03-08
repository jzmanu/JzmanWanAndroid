package com.manu.wanandroid.bean

/**
 * @Desc: 积分
 * @Author: jzman
 */
data class Integral(
    val coinCount: Int,
    val date: Long,
    val desc: String,
    val id: Int,
    val reason: String,
    val type: Int,
    val userId: Int,
    val userName: String
)
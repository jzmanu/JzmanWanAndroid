package com.manu.wanandroid.bean

/**
 * @Desc: 积分
 * @Author: jzman
 * @Date: 2021/2/1
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
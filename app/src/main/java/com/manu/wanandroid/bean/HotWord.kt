package com.manu.wanandroid.bean

/**
 * @Desc: 搜索热词
 * @Author: jzman
 * @Date: 2021/5/3 12:14.
 */
data class HotWord(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)
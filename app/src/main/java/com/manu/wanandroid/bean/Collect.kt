package com.manu.wanandroid.bean

/**
 * @Desc: 收藏列表bean
 * @Author: jzman
 * @Date: 2021/1/30 
 */
data class Collect(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val originId: Int,
    val publishTime: Long,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)
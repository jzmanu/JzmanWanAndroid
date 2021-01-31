package com.manu.wanandroid.bean

/**
 * @Desc: 仅适用于我的分享
 * @Author: jzman
 * @Date: 2021/1/31
 */
data class Share<T>(
        val coinInfo: CoinInfo,
        val shareArticles: ShareArticles<T>
)

data class CoinInfo(
        val coinCount: Int,
        val level: Int,
        val nickname: String,
        val rank: String,
        val userId: Int,
        val username: String
)

data class ShareArticles<T>(
        val curPage: Int,
        val datas: List<T>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)




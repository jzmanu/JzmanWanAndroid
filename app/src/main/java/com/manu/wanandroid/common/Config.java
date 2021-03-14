package com.manu.wanandroid.common;

import com.manu.wanandroid.utils.SharePreferenceHelperKt;

/**
 * @Desc: 全局配置
 * @Author: jzman
 */
public class Config {
    /**
     * 骨架显示时间
     */
    public static final int skeletonDuration = 1000;
    /**
     * 是否开启登录验证，默认关闭
     */
    public static final boolean configStartLoginAuth =
            SharePreferenceHelperKt.getSpValue(Common.config_start_login, false);

    /**
     * 是否wifi下自动下载Apk更新，默认关闭
     */
    public static final boolean configAutoDownloadUpgradeOnWifi =
            SharePreferenceHelperKt.getSpValue(Common.config_auto_download_upgrade, false);

}
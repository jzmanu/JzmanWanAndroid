package com.manu.wanandroid.common;

/**
 * App状态管理
 * @Desc: AppStatusTrack
 * @Author: jzman
 * @Date: 2019/5/8 0008 16:36
 */
public class AppStatusTrack {

    public static final String ACTION_HOME = "home_action";

    public static final String STATUS_FOCUS_KILLED = "focus_killed";

    private int mAppStatus;

    public static AppStatusTrack getInstance() {
        return Holder.INSTANCE;
    }

    public int getAppStatus() {
        return mAppStatus;
    }

    public void setAppStatus(int mAppStatus) {
        this.mAppStatus = mAppStatus;
    }

    private static class Holder {
        static AppStatusTrack INSTANCE = new AppStatusTrack();
    }
}

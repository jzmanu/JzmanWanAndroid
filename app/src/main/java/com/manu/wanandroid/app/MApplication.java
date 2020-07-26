package com.manu.wanandroid.app;

import com.manu.wanandroid.R;
import com.manu.wanandroid.common.AppStatusTrack;
import com.manu.wanandroid.di.component.AppComponent;
import com.manu.wanandroid.di.component.DaggerAppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import io.flutter.app.FlutterApplication;

/**
 * @Desc: MApplication
 * @Author: jzman
 * @Date: 2019/5/7 0007 16:38
 */
public class MApplication extends FlutterApplication {
    private static final String TAG = MApplication.class.getSimpleName();
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppStatusTrack.getInstance().setAppStatus(-1);
        mAppComponent = DaggerAppComponent.create();
        mAppComponent.injectApp(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context);
        }));
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

}
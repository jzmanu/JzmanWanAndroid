package com.manu.wanandroid.ui.main.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.manu.wanandroid.app.App;
import com.manu.wanandroid.base.activity.BaseMvpFlutterActivity;
import com.manu.wanandroid.bean.User;
import com.manu.wanandroid.contract.account.LoginContract;
import com.manu.wanandroid.di.component.activity.DaggerAgentActivityComponent;
import com.manu.wanandroid.presenter.account.LoginPresenter;
import com.manu.wanandroid.utils.L;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

/**
 * @Desc: AgentActivity
 * @Author: jzman
 */
public class AgentActivity extends BaseMvpFlutterActivity<LoginContract.Presenter> implements LoginContract.View {

    private static final String TAG = AgentActivity.class.getSimpleName();
    private static final String CHANNEL = "com.manu.startMainActivity";

    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onInject() {
        App mApplication = (App) getApplication();
        DaggerAgentActivityComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .build()
                .injectAgentActivity(this);
    }

    @Override
    public void configureFlutterEngine(@NotNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        L.i(TAG, "configureFlutterEngine");
        new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL)
                .setMethodCallHandler((methodCall, result) -> {
                    if ("login".equals(methodCall.method)) {
                        if (mLoginPresenter != null) {
                            mLoginPresenter.login(methodCall.argument("username"), methodCall.argument("password"));
                        }
                        result.success("success");
                    } else {
                        result.notImplemented();
                    }
                });
    }

    @Override
    protected LoginContract.Presenter onPresenter() {
        return mLoginPresenter;
    }

    @Override
    public void onLoginSuccess(@NotNull User user) {
        L.i(TAG, "onLoginSuccess");
        finish();
    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        L.i(TAG, "onShowErrorMessage > message:" + message);
        toast(message);
    }

    /**
     * 跳转到登陆页面
     *
     * @param activity 上下文
     */
    public static void startLoginActivity(AppCompatActivity activity) {
        Intent intent = AgentActivity
                .withNewEngine()
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
                .build(activity);
        activity.startActivity(intent);

        activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    L.i(TAG,"registerForActivityResult");
                }).launch(intent);
    }

    public static Intent createDefaultIntent(@NonNull Context launchContext) {
        return withNewEngine().build(launchContext);
    }

    public static FlutterActivity.NewEngineIntentBuilder withNewEngine() {
        return new MNewEngineIntentBuilder(AgentActivity.class);
    }

    static class MNewEngineIntentBuilder extends NewEngineIntentBuilder {

        protected MNewEngineIntentBuilder(Class<? extends FlutterActivity> activityClass) {
            super(activityClass);
        }
    }

    public static FlutterActivity.CachedEngineIntentBuilder withCacheEngine(String engineId) {
        return new MCacheEngineIntentBuilder(AgentActivity.class, engineId);
    }

    static class MCacheEngineIntentBuilder extends CachedEngineIntentBuilder {

        protected MCacheEngineIntentBuilder(@NonNull Class<? extends FlutterActivity> activityClass, @NonNull String engineId) {
            super(activityClass, engineId);
        }
    }
}
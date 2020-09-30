package com.manu.wanandroid.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.manu.wanandroid.utils.L;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;


public class AgentActivity extends FlutterActivity {

    private static final String TAG = AgentActivity.class.getSimpleName();
    private static final String CHANNEL = "com.manu.startMainActivity";
    
    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        L.i(TAG, "configureFlutterEngine");
        new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL)
                .setMethodCallHandler((methodCall, result) -> {
                    if ("startMainActivity".equals(methodCall.method)) {
                        MainActivity.startMainActivity(this);
                        finish();
                        result.success("success");
                    } else {
                        result.notImplemented();
                    }
                });
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
package com.manu.wanandroid.ui.main.activity;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

import android.content.Intent;
import android.os.Bundle;

import com.manu.wanandroid.utils.L;

public class AgentActivity extends FlutterActivity {

    private static final String TAG = AgentActivity.class.getSimpleName();
    private static final String CHANNEL = "com.manu.startMainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        L.i(TAG,"configureFlutterEngine");

        new MethodChannel(flutterEngine.getDartExecutor(),CHANNEL)
                .setMethodCallHandler((methodCall, result) -> {
                    if ("startMainActivity".equals(methodCall.method)){
                        MainActivity.startMainActivity(this);
                        result.success("success");
                    }else{
                        result.notImplemented();
                    }
                });
    }

    public static FlutterActivity.NewEngineIntentBuilder withNewEngine(){
        return new MNewEngineIntentBuilder(AgentActivity.class);
    }

    static class MNewEngineIntentBuilder extends NewEngineIntentBuilder{

        protected MNewEngineIntentBuilder(Class<? extends FlutterActivity> activityClass) {
            super(activityClass);
        }
    }

}
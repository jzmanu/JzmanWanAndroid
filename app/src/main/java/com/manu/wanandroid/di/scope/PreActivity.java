package com.manu.wanandroid.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义Scope便于管理Component
 * @Desc: PreActivity
 * @Author: jzman
 * @Date: 2019/5/9 0009 16:15
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PreActivity {
}

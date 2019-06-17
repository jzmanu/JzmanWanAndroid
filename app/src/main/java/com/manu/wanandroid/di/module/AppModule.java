package com.manu.wanandroid.di.module;

import com.manu.wanandroid.BuildConfig;
import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.http.IHttpHelper;
import com.manu.wanandroid.http.api.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Desc: AppModule
 * @Author: jzman
 * @Date: 2019/5/8 0008 14:31
 */

@Module
public class AppModule {
//    private final MApplication mApplication;
//
//    public AppModule(MApplication application) {
//        this.mApplication = application;
//    }

//    @Singleton
//    @Provides
//    MApplication providerApplication(){
//        return mApplication;
//    }

    @Singleton
    @Provides
    OkHttpClient.Builder providerOkHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient providerOkHttpClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            // 设置OkHttp日志拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        // 设置超时
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);

        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit.Builder providerRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    Retrofit providerRetrofit(OkHttpClient client, Retrofit.Builder builder) {
        return builder
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    ApiService providerApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}

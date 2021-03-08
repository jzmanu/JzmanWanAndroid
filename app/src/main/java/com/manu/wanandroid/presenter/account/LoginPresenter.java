package com.manu.wanandroid.presenter.account;

import com.google.gson.Gson;
import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.bean.User;
import com.manu.wanandroid.common.Account;
import com.manu.wanandroid.common.Common;
import com.manu.wanandroid.contract.account.LoginContract;
import com.manu.wanandroid.http.api.ApiService;
import com.manu.wanandroid.http.rx.BaseObserver;
import com.manu.wanandroid.http.rx.RxUtil;
import com.manu.wanandroid.model.DataManager;
import com.manu.wanandroid.base.mvp.presenter.BasePresenter;
import com.manu.wanandroid.utils.SharePreferenceHelperKt;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * @Desc: LoginPresenter
 * @Author: jzman
 * @Date: 2021/1/18 21:57.
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void login(@NotNull String username, @NotNull String password) {
        addRxSubscribe(mDataManager.login(username, password)
                .compose(RxUtil.rxSchedulers())
                .compose(RxUtil.rxHandlerResult())
                .subscribeWith(new BaseObserver<User>(mView) {
                    @Override
                    public void onNext(@NonNull User user) {
                        List<Cookie> cookies = MApplication.getApp()
                                .getCookieJar()
                                .loadForRequest(Objects.requireNonNull(HttpUrl.parse(ApiService.BASE_URL + Account.LOGIN)));
                        for (Cookie cookie : cookies) {
                            if (cookie != null && cookie.expiresAt() != 0) {
                                SharePreferenceHelperKt.putSpValue(Account.COOKIE_EXPIRES, cookie.expiresAt());
                                break;
                            }
                        }
                        String userJson = Common.INSTANCE.getGson().toJson(user);
                        SharePreferenceHelperKt.putSpValue(Account.USER_INFO, userJson);
                        mView.onLoginSuccess(user);
                    }
                }));
    }
}

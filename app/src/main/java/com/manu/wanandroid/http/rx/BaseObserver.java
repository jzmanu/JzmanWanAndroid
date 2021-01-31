package com.manu.wanandroid.http.rx;

import com.google.gson.JsonParseException;
import com.manu.wanandroid.http.exception.MException;
import com.manu.wanandroid.http.exception.MExceptionFactory;
import com.manu.wanandroid.mvp.view.IView;
import com.manu.wanandroid.utils.L;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @Desc: BaseObserver
 * @Author: jzman
 * @Date: 2019/5/10 0010 15:04
 */
public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private static final String TAG = BaseObserver.class.getSimpleName();
    private IView mView;

    public BaseObserver(IView iView) {
        this.mView = iView;
    }

    @Override
    public void onError(Throwable e) {
        L.i(TAG, e.getMessage());
        if (e instanceof HttpException) {
            mView.onShowErrorMessage("客户端错误");
        } else if (e instanceof SocketTimeoutException) {
            mView.onShowErrorMessage("SocketTimeoutException:" + e.getMessage());
        } else if (e instanceof ConnectException) {
            mView.onShowErrorMessage("ConnectException:" + e.getMessage());
        } else if (e instanceof JsonParseException) {
            mView.onShowErrorMessage("Json解析失败");
        } else if (e instanceof UnknownHostException) {
            mView.onShowErrorMessage("未连接网络");
        } else if (e instanceof MException){
            mView.onShowErrorMessage(((MException) e).getErrorMessage());
        }else{
            mView.onShowErrorMessage("未知错误");
        }
    }

    @Override
    public void onComplete() {
        L.i(TAG, "onComplete");
    }
}

package com.manu.wanandroid.base.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.manu.wanandroid.base.mvp.presenter.IPresenter;
import com.manu.wanandroid.base.mvp.view.IView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import io.flutter.embedding.android.FlutterActivity;

/**
 * @Desc: Flutter混合开发专用
 * @Author: jzman
 * @Date: 2021/1/18 23:25.
 */
@SuppressWarnings("all")
public abstract class BaseMvpFlutterActivity<T extends IPresenter> extends FlutterActivity implements IView {
    private static final String TAG = BaseMvpFlutterActivity.class.getSimpleName();
    protected T mPresenter;

    protected abstract T onPresenter();

    protected abstract void onInject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInject();
        mPresenter = onPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNormalContent() {

    }

    @Override
    public void onHideNormalContent() {

    }

    @Override
    public void onShowErrorMessage(String message) {

    }

    @Override
    public void onShowEmptyMessage() {

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    protected void toast(@NonNull String message){
        if (message == null) return;
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(@StringRes int messageId){
        if (messageId <= 0) return;
        Toast.makeText(this,messageId, Toast.LENGTH_SHORT).show();
    }
}

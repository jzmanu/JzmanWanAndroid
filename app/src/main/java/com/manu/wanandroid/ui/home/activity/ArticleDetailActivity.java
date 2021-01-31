package com.manu.wanandroid.ui.home.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;

import com.google.android.material.snackbar.Snackbar;
import com.manu.wanandroid.R;
import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.base.activity.BaseMvpActivity;
import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.common.Account;
import com.manu.wanandroid.contract.home.CollectContract;
import com.manu.wanandroid.databinding.ActivityArticleDetailBinding;
import com.manu.wanandroid.di.component.activity.DaggerArticleDetailActivityComponent;
import com.manu.wanandroid.presenter.home.CollectPresenter;
import com.manu.wanandroid.ui.main.activity.AgentActivity;
import com.manu.wanandroid.utils.L;
import com.manu.wanandroid.utils.StatusBarUtil;
import com.manu.wanandroid.web.MWebChromeClient;
import com.manu.wanandroid.web.MWebViewClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Desc: ArticleDetailActivity
 * @Author: jzman
 * @Date: 2019/5/20 0020 10:56
 */
public class ArticleDetailActivity extends BaseMvpActivity<CollectContract.Presenter> implements CollectContract.View {

    private static final String TAG = ArticleDetailActivity.class.getSimpleName();

    public static final String PARAM_ID = "param_id";
    public static final String PARAM_URL = "param_url";
    public static final String PARAM_COLLECT = "param_collect";
    public static final String PARAM_ONLY_BROWSER = "param_only_browser";

    @Inject
    CollectPresenter mCollectPresenter;

    private int mId;
    private boolean isCollect;
    private ActivityArticleDetailBinding binding;

    @Override
    public View onLayout() {
        binding = ActivityArticleDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onInject() {
        MApplication mMApplication = (MApplication) getApplication();
        DaggerArticleDetailActivityComponent.builder()
                .appComponent(mMApplication.getAppComponent())
                .build()
                .injectArticleDetailActivity(this);
    }

    @Override
    protected CollectContract.Presenter onPresenter() {
        return mCollectPresenter;
    }

    @Override
    public void onInitToolbar() {
        binding.loadingProgressBar.setMax(100);
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
        StatusBarUtil.setDarkMode(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onInitData() {
        Intent intent = getIntent();
        if (intent != null) {
            mId = intent.getIntExtra(PARAM_ID, -1);
            isCollect = intent.getBooleanExtra(PARAM_COLLECT, false);
            boolean isBrowser = intent.getBooleanExtra(PARAM_ONLY_BROWSER, false);
            binding.fab.setActivated(isCollect);
            if (isBrowser) binding.fab.hide();
            String mUrl = intent.getStringExtra(PARAM_URL);

            WebSettings webSettings = binding.webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            binding.webView.setWebViewClient(new MWebViewClient(webSettings.getUserAgentString()));
            binding.webView.setWebChromeClient(new MWebChromeClient(binding.loadingProgressBar, binding.fab));
            binding.webView.loadUrl(mUrl);

            binding.webView.setOnDoubleClickListener(v -> finish());
            binding.fab.setOnClickListener(v -> {
                if (Account.INSTANCE.isLogin()) {
                    if (isCollect){
                        mCollectPresenter.unCollectArticle(String.valueOf(mId));
                    }else {
                        mCollectPresenter.collectArticle(String.valueOf(mId));
                    }
                } else {
                    AgentActivity.startLoginActivity(this);
                }
            });
        }
    }

    @Override
    public void onCollectArticleSuccess() {
        L.i(TAG, "onCollectArticleSuccess");
        Snackbar.make(binding.webView, R.string.common_collect_success, Snackbar.LENGTH_SHORT).show();
        binding.fab.setActivated(true);
        isCollect = true;

    }

    @Override
    public void onUnCollectArticleSuccess() {
        L.i(TAG, "onUnCollectArticleSuccess");
        Snackbar.make(binding.webView, R.string.common_collect_cancel, Snackbar.LENGTH_SHORT).show();
        binding.fab.setActivated(false);
        isCollect = false;
    }

    @Override
    public void onGetCollectArticleSuccess(@NotNull List<Collect> result) {

    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        toast(message);
    }

    public static void startArticleDetailActivity(AppCompatActivity activity, int id, String url, boolean collect) {
        startArticleDetailActivity(activity,id,url,collect,false);
    }

    public static void startArticleDetailActivityOnlyBrowser(AppCompatActivity activity, int id, String url) {
        startArticleDetailActivity(activity,id,url,false,true);
    }

    public static void startArticleDetailActivity(AppCompatActivity activity, int id, String url, boolean collect, boolean onlyBrowser) {
        Intent intent = new Intent(activity, ArticleDetailActivity.class);
        intent.putExtra(PARAM_ID, id);
        intent.putExtra(PARAM_URL, url);
        intent.putExtra(PARAM_COLLECT, collect);
        intent.putExtra(PARAM_ONLY_BROWSER, onlyBrowser);
        activity.startActivity(intent);
    }
}

package com.manu.wanandroid.ui.home.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;

import com.google.android.material.snackbar.Snackbar;
import com.manu.wanandroid.R;
import com.manu.wanandroid.app.App;
import com.manu.wanandroid.base.activity.BaseMvpActivity;
import com.manu.wanandroid.bean.Collect;
import com.manu.wanandroid.common.Account;
import com.manu.wanandroid.contract.home.CollectContract;
import com.manu.wanandroid.databinding.ActivityArticleDetailBinding;
import com.manu.wanandroid.di.component.activity.DaggerArticleDetailActivityComponent;
import com.manu.wanandroid.presenter.home.MineCollectPresenter;
import com.manu.wanandroid.ui.main.activity.AgentActivity;
import com.manu.wanandroid.utils.L;
import com.manu.wanandroid.utils.StatusBarUtil;
import com.manu.wanandroid.web.MWebChromeClient;
import com.manu.wanandroid.web.MWebViewClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
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

    public static final String ARTICLE_REFRESH = "article_refresh";

    @Inject
    MineCollectPresenter mCollectPresenter;

    private int mId;
    private static boolean isCollect;
    private static boolean isRefresh;
    private ActivityArticleDetailBinding binding;

    @Override
    public View onLayout() {
        binding = ActivityArticleDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onInject() {
        App mApp = (App) getApplication();
        DaggerArticleDetailActivityComponent.builder()
                .appComponent(mApp.getAppComponent())
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
            isRefresh = isCollect;
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

            binding.webView.setOnDoubleClickListener(v -> {
                setResult();
                finish();
            });
            binding.fab.setOnClickListener(v -> {
                if (Account.INSTANCE.isLogin()) {
                    collectOrUnCollect();
                } else {
                    AgentActivity.startLoginActivityForResult(this, result -> {
                        collectOrUnCollect();
                    });
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) setResult();
        return super.onKeyDown(keyCode, event);
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
        startArticleDetailActivity(activity, id, url, collect, false, null);
    }

    public static void startArticleDetailActivityForResult(
            AppCompatActivity activity, int id, String url,
            boolean collect, ActivityResultCallback<ActivityResult> callback) {
        startArticleDetailActivity(activity, id, url, collect, false, callback);
    }

    public static void startArticleDetailActivityOnlyBrowser(AppCompatActivity activity, int id, String url) {
        startArticleDetailActivity(activity, id, url, false, true, null);
    }

    public static void startArticleDetailActivity(
            AppCompatActivity activity, int id, String url,
            boolean collect, boolean onlyBrowser, ActivityResultCallback<ActivityResult> callback) {
        Intent intent = new Intent(activity, ArticleDetailActivity.class);
        intent.putExtra(PARAM_ID, id);
        intent.putExtra(PARAM_URL, url);
        intent.putExtra(PARAM_COLLECT, collect);
        intent.putExtra(PARAM_ONLY_BROWSER, onlyBrowser);
        activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback).launch(intent);
    }

    /**
     * 收藏还是取消收藏
     */
    private void collectOrUnCollect() {
        if (isCollect) {
            mCollectPresenter.unCollectArticle(String.valueOf(mId));
        } else {
            mCollectPresenter.collectArticle(String.valueOf(mId));
        }
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra(ARTICLE_REFRESH, isRefresh == !isCollect);
        setResult(RESULT_OK,intent);
    }
}

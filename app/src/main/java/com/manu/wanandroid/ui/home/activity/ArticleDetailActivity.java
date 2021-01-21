package com.manu.wanandroid.ui.home.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;

import com.manu.wanandroid.R;
import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.base.activity.BaseMvpActivity;
import com.manu.wanandroid.common.Account;
import com.manu.wanandroid.contract.home.CollectContract;
import com.manu.wanandroid.di.component.activity.DaggerArticleDetailActivityComponent;
import com.manu.wanandroid.presenter.home.CollectPresenter;
import com.manu.wanandroid.ui.main.activity.AgentActivity;
import com.manu.wanandroid.utils.L;
import com.manu.wanandroid.utils.StatusBarUtil;
import com.manu.wanandroid.web.MWebChromeClient;
import com.manu.wanandroid.web.MWebViewClient;
import com.manu.wanandroid.widget.MWebView;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import butterknife.BindView;

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

    @Inject
    CollectPresenter mCollectPresenter;

    @BindView(R.id.webView)
    MWebView webView;
    @BindView(R.id.loadingProgressBar)
    ContentLoadingProgressBar loadingProgressBar;

    private int mId;
    private boolean isCollect;

    @Override
    public int onLayoutId() {
        return R.layout.activity_article_detail;
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
        loadingProgressBar.setMax(100);
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
        StatusBarUtil.setDarkMode(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onInitData() {

        Intent intent = getIntent();
        if (intent != null) {
            mId = intent.getIntExtra(PARAM_ID,  -1);
            isCollect = intent.getBooleanExtra(PARAM_COLLECT, false);
            String mUrl = intent.getStringExtra(PARAM_URL);

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webView.setWebViewClient(new MWebViewClient(webSettings.getUserAgentString()));
            webView.setWebChromeClient(new MWebChromeClient(loadingProgressBar));
            webView.loadUrl(mUrl);

            webView.setOnDoubleClickListener(v -> {
                if (Account.INSTANCE.isLogin()){
                    toast("收藏");
                    mCollectPresenter.collectArticle(String.valueOf(mId));
                }else{
                    AgentActivity.startLoginActivity(this);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.collect_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.home_collect:
                item.setIcon(R.drawable.home_collect_yes_24dp);
                break;
        }
        return true;
    }

    @Override
    public void onCollectArticleSuccess() {
        L.i(TAG, "onCollectArticleSuccess");
    }

    @Override
    public void onUnCollectArticleSuccess() {
        L.i(TAG, "onUnCollectArticleSuccess");
    }

    @Override
    public void onShowErrorMessage(String message) {
        super.onShowErrorMessage(message);
        toast(message);
    }

    public static void startArticleDetailActivity(AppCompatActivity activity, int id, String url, boolean collect) {
        Intent intent = new Intent(activity, ArticleDetailActivity.class);
        intent.putExtra(PARAM_ID, id);
        intent.putExtra(PARAM_URL, url);
        intent.putExtra(PARAM_COLLECT, collect);
        activity.startActivity(intent);
    }
}

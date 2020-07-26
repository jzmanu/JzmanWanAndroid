package com.manu.wanandroid.ui.home.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.manu.wanandroid.R;
import com.manu.wanandroid.app.MApplication;
import com.manu.wanandroid.base.activity.BaseMvpActivity;
import com.manu.wanandroid.contract.home.CollectContract;
import com.manu.wanandroid.di.component.activity.DaggerArticleDetailActivityComponent;
import com.manu.wanandroid.presenter.home.CollectPresenter;
import com.manu.wanandroid.utils.L;
import com.manu.wanandroid.utils.StatusBarUtil;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Inject
    CollectPresenter mCollectPresenter;
    @BindView(R.id.webView)
    WebView webView;

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
    public void onInitToolbar() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("文章详情页");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onInitData() {

        Intent intent = getIntent();
        if (intent != null) {
            mId = intent.getIntExtra(PARAM_ID, -1);
            isCollect = intent.getBooleanExtra(PARAM_COLLECT, false);
            String mUrl = intent.getStringExtra(PARAM_URL);

            webView.loadUrl(mUrl);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
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
        L.i(TAG, "onHomeArticleListSuccess");
    }

    @Override
    public void onUnCollectArticleSuccess() {
        L.i(TAG, "onUnCollectArticleSuccess");
    }

    public static void startArticleDetailActivity(AppCompatActivity activity, int id, String url, boolean collect) {
        Intent intent = new Intent(activity, ArticleDetailActivity.class);
        intent.putExtra(PARAM_ID, id);
        intent.putExtra(PARAM_URL, url);
        intent.putExtra(PARAM_COLLECT, collect);
        activity.startActivity(intent);
    }

}

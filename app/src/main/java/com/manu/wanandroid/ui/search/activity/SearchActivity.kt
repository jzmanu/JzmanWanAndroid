package com.manu.wanandroid.ui.search.activity

import android.app.SearchManager
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.manu.wanandroid.R
import com.manu.wanandroid.app.App
import com.manu.wanandroid.base.activity.BaseActivity
import com.manu.wanandroid.databinding.ActivitySearchBinding
import com.manu.wanandroid.di.component.activity.DaggerSearchActivityComponent
import com.manu.wanandroid.di.component.activity.SearchActivityComponent
import com.manu.wanandroid.ui.search.fragment.SearchFragment
import com.manu.wanandroid.utils.L
import com.manu.wanandroid.utils.StatusBarUtil
import javax.inject.Inject


class SearchActivity : BaseActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySearchBinding

    @Inject
    lateinit var searchFragment: SearchFragment

    lateinit var searchActivityComponent: SearchActivityComponent

    override fun onLayout(): View {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onToolbar() {
        super.onToolbar()
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
        StatusBarUtil.setImmerseStatusBarSystemUiVisibility(this)
    }

    override fun onInject() {
        val mMApplication = application as App
        searchActivityComponent = DaggerSearchActivityComponent.builder()
                .appComponent(mMApplication.appComponent)
                .build()
        searchActivityComponent.injectSearchActivity(this)
    }

    override fun onData() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_container, searchFragment)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // 加载menu文件
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_items, menu)
        // SearchView
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        val hintView = searchView.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)

        // SearchView设置
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnCloseListener {
            L.i(TAG, "searchView close")
            false
        }

        searchView.queryHint = getString(R.string.common_search)
        hintView.setHintTextColor(resources.getColor(R.color.colorSearch))
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        L.i(TAG, "onOptionsItemSelected > itemId:${item?.itemId}")
        when (item?.itemId) {
            R.id.action_search -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        L.i(TAG, "onQueryTextSubmit > newText:$query")
        // 点击提交按钮的时候
        searQuery(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        L.i(TAG, "onQueryTextChange > newText:$newText")
        if (TextUtils.isEmpty(newText)) {
            searQuery("")
            return true
        }
        return false
    }

    private fun searQuery(query: String) {
        searchFragment.search(query)
    }

    companion object {
        private val TAG = SearchActivity::class.java.simpleName
    }
}
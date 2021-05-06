package com.manu.wanandroid.ui.main.fragment

import android.view.View
import com.manu.wanandroid.base.fragment.BaseFragment
import com.manu.wanandroid.databinding.FragmentMineBinding
import com.manu.wanandroid.di.module.fragment.MineFragmentModule
import com.manu.wanandroid.ui.main.activity.MainActivity
import com.manu.wanandroid.utils.L

/**
 * @Desc: MineFragment
 * @Author: jzman
 */
class MineFragment : BaseFragment() {
    private lateinit var binding: FragmentMineBinding
    private var mActivity: MainActivity? = null
    override fun onLayout(): View {
        binding = FragmentMineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onInject() {
        mActivity = activity as MainActivity
        mActivity!!.mMainActivityComponent
                .getMineFragmentComponent(MineFragmentModule())
                .injectMineFragment(this)
    }

    override fun onData() {
        L.i(TAG, "onInitData")

    }

    companion object {
        private val TAG = MineFragment::class.java.simpleName
    }
}
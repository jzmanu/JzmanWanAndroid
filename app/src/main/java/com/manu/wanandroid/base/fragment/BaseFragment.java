package com.manu.wanandroid.base.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @Desc: BaseFragment
 * @Author: jzman
 * @Date: 2019/5/9 0009 10:26
 */
public abstract class BaseFragment extends Fragment {

    private boolean isFirstLoad = true;
    private boolean isInitView = false;

    public BaseFragment() {
        // Required empty public constructor
    }

    public abstract View onLayout();

    public abstract void onInject();

    public void onInitMessageView() {
    }

    public abstract void onInitData();

    public void onLazyLoad() {
    }

    public void onAttach() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = onLayout();
        onInject();
        onAttach();
        onInitData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInitMessageView();
        isInitView = true;
        //第一次加载且用户可见时加载数据
        if (isFirstLoad && getUserVisibleHint()) {
            onLazyLoad();
            isFirstLoad = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isInitView) {
            onLazyLoad();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
        isInitView = false;
    }

    protected void toast(String message){
        if (message == null) return;
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(@StringRes int messageId){
        Toast.makeText(getActivity(),messageId, Toast.LENGTH_SHORT).show();
    }
}

package com.example.test_kao.base;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.test_kao.interfaces.IBaseView;
import com.example.test_kao.ui.login.LoginActivity;
import com.example.test_kao.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected P persenter;
    Unbinder unbinder;
    protected Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        //注册黄油刀
        unbinder = ButterKnife.bind(this,view);
        persenter = createPersenter();
        if(persenter != null){
            persenter.attachView(this);
        }

        initView();
        initData();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract P createPersenter();

    public abstract void initData();

    /**
     * 跳转登录
     */
//    protected void gotoLogin(){
//        ActivityUtils.startFragmentForResult(this,100, LoginActivity.class);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(persenter != null){
            persenter.unAttachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }

    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }
}

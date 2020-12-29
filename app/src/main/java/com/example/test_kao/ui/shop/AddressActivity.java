package com.example.test_kao.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test_kao.R;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.interfaces.IBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
//TODO 收货地址的列表
public class AddressActivity extends BaseActivity {
    @BindView(R.id.new_address)
    Button newAddress;

    @Override
    protected int getLayout() {
        return R.layout.activity_address;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        newAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,AddProvinceActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

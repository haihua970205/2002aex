package com.example.test_kao.ui.home.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kao.R;
import com.example.test_kao.adapter.Car_TalkAdapter;
import com.example.test_kao.app.MyApp;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.bean.TalkBean;
import com.example.test_kao.interfaces.IBasePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanAllActivity extends BaseActivity {
    @BindView(R.id.rlv_talk)
    RecyclerView rlvTalk;

    @Override
    protected int getLayout() {
        return R.layout.activity_can_all;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        List<TalkBean.DataBeanX.DataBean> list =
                (List<TalkBean.DataBeanX.DataBean>) MyApp.getMap().get("list");
        rlvTalk.setLayoutManager(new LinearLayoutManager(this));
        rlvTalk.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        Car_TalkAdapter car_talkAdapter = new Car_TalkAdapter(this, list);
        rlvTalk.setAdapter(car_talkAdapter);
        car_talkAdapter.notifyDataSetChanged();
    }
}

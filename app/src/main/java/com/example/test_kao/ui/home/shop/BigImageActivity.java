package com.example.test_kao.ui.home.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.test_kao.R;
import com.example.test_kao.adapter.BigVpAdapter;
import com.example.test_kao.app.MyApp;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.interfaces.IBasePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BigImageActivity extends BaseActivity {
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.vp_big)
    ViewPager vpBig;
    @BindView(R.id.tv_return)
    ImageView tvReturn;

    private List<String> big;

    @Override
    protected int getLayout() {
        return R.layout.activity_bigimage;
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
        int pos = intent.getIntExtra("pos", 0);
        big = (List<String>) MyApp.getMap().get("big");
        Log.e("111", "initData: " + big);

        size.setText(big.size() + "");
        count.setText(pos + 1 + "");

        BigVpAdapter bigVpAdapter = new BigVpAdapter(this, big);
        vpBig.setAdapter(bigVpAdapter);

        vpBig.setCurrentItem(pos);

        vpBig.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                count.setText(position + 1 + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.tv_return)
    public void onViewClicked() {
        finishAndRemoveTask();//关闭当前页面返回之前页面
    }
}

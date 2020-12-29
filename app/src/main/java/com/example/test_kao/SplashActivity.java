package com.example.test_kao;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.ui.startpage.SplashFragment;
import com.example.test_kao.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.img_no1)
    ImageView imgNo1;
    @BindView(R.id.img_select1)
    ImageView imgSelect1;
    @BindView(R.id.img_no2)
    ImageView imgNo2;
    @BindView(R.id.img_select2)
    ImageView imgSelect2;
    @BindView(R.id.img_no3)
    ImageView imgNo3;
    @BindView(R.id.img_select3)
    ImageView imgSelect3;
    private List<SplashFragment> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
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
        list = new ArrayList<>();
        list.add(SplashFragment.getInstance(1));
        list.add(SplashFragment.getInstance(2));
        list.add(SplashFragment.getInstance(3));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetDots();
                if(position == 0){
                    imgNo1.setVisibility(View.GONE);
                    imgSelect1.setVisibility(View.VISIBLE);
                }else if(position == 1){
                    imgNo2.setVisibility(View.GONE);
                    imgSelect2.setVisibility(View.VISIBLE);
                }else if(position == 2){
                    imgNo3.setVisibility(View.GONE);
                    imgSelect3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void resetDots(){
        imgNo1.setVisibility(View.VISIBLE);
        imgNo2.setVisibility(View.VISIBLE);
        imgNo3.setVisibility(View.VISIBLE);
        imgSelect1.setVisibility(View.GONE);
        imgSelect2.setVisibility(View.GONE);
        imgSelect3.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        String firstShow = SpUtils.getInstance().getString("firstShow");
        if (!TextUtils.isEmpty(firstShow)){
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }else {
            SpUtils.getInstance().setValue("firstShow","以展示");
        }
    }
}

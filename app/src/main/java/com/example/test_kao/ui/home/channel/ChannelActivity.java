package com.example.test_kao.ui.home.channel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.test_kao.R;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.TypeBean;
import com.example.test_kao.frag.ChannelFragment;
import com.example.test_kao.interfaces.ICate;
import com.example.test_kao.presenter.InfoPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends BaseActivity<InfoPresenter> implements ICate.View {


    private TabLayout mTab;
    private CustomViewPager mMainVp;
    private  boolean isInit = false;
    private int i;
    private List<TypeBean.DataBean.BrotherCategoryBean> brotherCategory;


    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected InfoPresenter createPrenter() {
        return new InfoPresenter( this );
    }

    @Override
    protected void initView() {
        mTab = (TabLayout) findViewById( R.id.tab );
        mMainVp = (CustomViewPager) findViewById( R.id.vp_main );

    }

    @Override
    protected void initData() {
        //网络获取
        String url = getIntent().getStringExtra( "substring" );
        if (url != null) {
            i = Integer.parseInt(url);
            presenter.getType(i);
        }
    }

    @Override
    public void getResult(TypeBean typeBean) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        brotherCategory = typeBean.getData().getBrotherCategory();
        for (int i = 0; i < brotherCategory.size(); i++) {

            int id = brotherCategory.get( i ).getId();
            String name = brotherCategory.get( i ).getName();
            String front_name = brotherCategory.get( i ).getFront_name();

            //創建fragment
            ChannelFragment channelFragment = new ChannelFragment();
            //bundle传值
            Bundle bundle = new Bundle();
            bundle.putInt( "id", id );
            bundle.putString( "name", name );
            bundle.putString( "front_name", front_name );

            channelFragment.setArguments( bundle );

            fragments.add( channelFragment );
        }

        mMainVp.setAdapter( new FragmentPagerAdapter( getSupportFragmentManager() ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get( position );
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        } );

        mTab.setupWithViewPager( mMainVp );
        for (int i = 0; i < brotherCategory.size(); i++) {
            //获取tab值
            String name = brotherCategory.get( i ).getName();
            //传值  用传来的名字和接口获得的做对比，选择对应的tab
            Intent intent = getIntent();
            String name1 = intent.getStringExtra( "name" );
            if (name1.equals( name )) {
                mTab.getTabAt( i ).select();
            }
            mTab.getTabAt( i ).setText( name );
        }
    }

    @Override
    public void getResult(InfoBean infoBean) {

    }
}
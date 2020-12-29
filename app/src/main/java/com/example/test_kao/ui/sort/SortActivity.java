package com.example.test_kao.ui.sort;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.test_kao.R;
import com.example.test_kao.app.MyApp;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.frag.CateFrag;
import com.example.test_kao.frag.CategoryTabFragment;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.interfaces.sort.IType;
import com.example.test_kao.presenter.sort.TypePresenter;
import com.example.test_kao.ui.home.HomeFrag;
import com.example.test_kao.ui.me.MeFrag;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SortActivity extends BaseActivity<TypePresenter>implements IType.View {
    @BindView(R.id.tab_ac)
    TabLayout tabAc;
    @BindView(R.id.vp_ac)
    ViewPager vpAc;
    private List<SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean> li;
    private String name;

    @Override
    protected int getLayout() {
        return R.layout.activity_sort;
    }

    @Override
    protected TypePresenter createPrenter() {
        return new TypePresenter(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if(intent.hasExtra("name")){
            name = intent.getStringExtra("name");
        }

        li = (List<SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean>) MyApp.getMap().get("li");
        Log.e("111", "getResult: "+li );
        ArrayList<Fragment> fragmentss = new ArrayList<>();
        ArrayList<String> tabName1 = new ArrayList<>();
        for(int i = 0; i< li.size(); i++){
            int id = li.get(i).getId();
            Sort_ch_frag sort_ch_frag = new Sort_ch_frag();
            fragmentss.add(sort_ch_frag);
            sort_ch_frag.setData(id);
            tabName1.add(li.get(i).getName());
            presenter.getSort(id);
        }
        vpAc.setAdapter( new FragmentPagerAdapter( getSupportFragmentManager() ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentss.get( position);
            }

            @Override
            public int getCount() {
                return fragmentss.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabName1.get(position);
            }
        });
        tabAc.setupWithViewPager(vpAc);
        for(int i=0;i<tabName1.size();i++){
            String s = tabName1.get(i);
            if(s.equals(name)){
                tabAc.getTabAt(i).select();
            }
        }
    }

    @Override
    public void getResult(VTypeBean vTypeBean) {
    }

    @Override
    public void getResult(SortBean sortBean) {
    }
}

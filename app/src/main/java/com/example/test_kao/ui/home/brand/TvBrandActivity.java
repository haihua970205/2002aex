package com.example.test_kao.ui.home.brand;

import android.content.Intent;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kao.R;
import com.example.test_kao.adapter.TvBrandAdapter;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.BrandIdBean;
import com.example.test_kao.bean.Brand_Tv_infoBean;
import com.example.test_kao.interfaces.IBrand;
import com.example.test_kao.presenter.BrandPresenter;

import java.util.List;

public class TvBrandActivity extends BaseActivity<BrandPresenter> implements IBrand.View {

    RecyclerView rlvTvBrand;

    @Override
    protected int getLayout() {
        return R.layout.activity_tvbrand;
    }

    @Override
    protected BrandPresenter createPrenter() {
        return new BrandPresenter(this);
    }

    @Override
    protected void initView() {
        rlvTvBrand = findViewById(R.id.rlv_tvBrand);
        rlvTvBrand.setLayoutManager(new LinearLayoutManager(this));
        rlvTvBrand.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));
    }

    @Override
    protected void initData() {
        presenter.getBrand(1, 1000);
    }

    @Override
    public void getResult(BrandBean brandBean) {
        List<BrandBean.DataBeanX.DataBean> data = brandBean.getData().getData();
        TvBrandAdapter tvBrandAdapter = new TvBrandAdapter(this, data);
        rlvTvBrand.setAdapter(tvBrandAdapter);
        tvBrandAdapter.notifyDataSetChanged();

        tvBrandAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(TvBrandActivity.this, Tv_brand_infoActivity.class);
                intent.putExtra("id",data.get(pos).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getResult(Brand_Tv_infoBean result) {

    }

    @Override
    public void getResult(BrandIdBean result) {

    }
}

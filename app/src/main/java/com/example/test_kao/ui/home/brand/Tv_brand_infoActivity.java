package com.example.test_kao.ui.home.brand;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.adapter.Brand_MUJI_Adapter;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.BrandIdBean;
import com.example.test_kao.bean.Brand_Tv_infoBean;
import com.example.test_kao.interfaces.IBrand;
import com.example.test_kao.presenter.BrandPresenter;
import com.example.test_kao.utils.TxtUtils;

import java.util.List;

public class Tv_brand_infoActivity extends BaseActivity<BrandPresenter> implements IBrand.View {

    private ImageView img_tvbrandinfo;
    private TextView name_tvbrandinfo;
    private TextView desc_tvbrandinfo;
    private RecyclerView rlv_brand_MUJI;

    @Override
    protected int getLayout() {
        return R.layout.activity_tv_brand_info;
    }

    @Override
    protected BrandPresenter createPrenter() {
        return new BrandPresenter(this);
    }

    @Override
    protected void initView() {
        img_tvbrandinfo = findViewById(R.id.img_tvbrandinfo);
        name_tvbrandinfo = findViewById(R.id.name_tvbrandinfo);
        desc_tvbrandinfo = findViewById(R.id.desc_tvbrandinfo);
        rlv_brand_MUJI  = findViewById(R.id.rlv_brand_MUJI);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        presenter.getBrandtvinfo(id);
        presenter.getBrandId(id);
    }

    @Override
    public void getResult(Brand_Tv_infoBean result) {
        Brand_Tv_infoBean.DataBean.BrandBean brand = result.getData().getBrand();
        Glide.with(this).load(brand.getApp_list_pic_url()).into(img_tvbrandinfo);
        TxtUtils.setTextView(name_tvbrandinfo,brand.getName());
        TxtUtils.setTextView(desc_tvbrandinfo,brand.getSimple_desc());
    }

    @Override
    public void getResult(BrandIdBean result) {
        List<BrandIdBean.DataBeanX.DataBean> data = result.getData().getData();
        rlv_brand_MUJI.setLayoutManager(new GridLayoutManager(this,2));
        Brand_MUJI_Adapter brand_muji_adapter = new Brand_MUJI_Adapter(this, data);
        rlv_brand_MUJI.setAdapter(brand_muji_adapter);
        brand_muji_adapter.notifyDataSetChanged();
    }

    @Override
    public void getResult(BrandBean brandBean) {

    }
}

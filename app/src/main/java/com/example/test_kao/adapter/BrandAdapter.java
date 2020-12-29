package com.example.test_kao.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.HomeBean;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    private Context context;
    public BrandAdapter(Context context, List<HomeBean.DataBean.BrandListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_brand;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.BrandListBean bean = (HomeBean.DataBean.BrandListBean) data;
        ImageView img = (ImageView) vh.getViewById(R.id.img);
        TextView name = (TextView) vh.getViewById(R.id.name);
        TextView price = (TextView) vh.getViewById(R.id.price);

        Glide.with(context).load(bean.getNew_pic_url()).into(img);
        name.setText(bean.getName());
        price.setText(bean.getFloor_price()+"元起");
    }
}

package com.example.test_kao.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.HomeBean;

import java.util.List;

public class TvBrandAdapter extends BaseAdapter {
    private Context context;
    public TvBrandAdapter(Context context, List<BrandBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_tv_brand;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandBean.DataBeanX.DataBean bean = (BrandBean.DataBeanX.DataBean) data;
        ImageView img_tvbrand = (ImageView) vh.getViewById(R.id.img_tvbrand);
        TextView name_tvbrand = (TextView) vh.getViewById(R.id.name_tvbrand);
        TextView pri_tvbrand = (TextView) vh.getViewById(R.id.pri_tvbrand);

        Glide.with(context).load(bean.getApp_list_pic_url()).into(img_tvbrand);
        name_tvbrand.setText(bean.getName());
        pri_tvbrand.setText(bean.getFloor_price());
    }
}

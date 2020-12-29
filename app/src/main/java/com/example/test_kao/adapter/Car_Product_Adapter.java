package com.example.test_kao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.RelateBean;

import java.util.List;

public class Car_Product_Adapter extends BaseAdapter {
    public Car_Product_Adapter(Context context, List<RelateBean.DataBean.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.car_product;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        RelateBean.DataBean.GoodsListBean bean = (RelateBean.DataBean.GoodsListBean) data;
        ImageView img_car_pro = (ImageView) vh.getViewById(R.id.img_car_pro);
        TextView car_pro_name= (TextView) vh.getViewById(R.id.car_pro_name);
        TextView pri_car_pro= (TextView) vh.getViewById(R.id.pri_car_pro);
        Glide.with(context).load(bean.getList_pic_url()).into(img_car_pro);
        car_pro_name.setText(bean.getName());
        pri_car_pro.setText("￥"+bean.getRetail_price());
        String s = pri_car_pro.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.RED),0,s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        pri_car_pro.setText(builder);
    }
}

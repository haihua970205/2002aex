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
import com.example.test_kao.bean.HotGoodsBean;

import java.util.List;

public class HotGoodsAdapter extends BaseAdapter {
    public HotGoodsAdapter(Context context, List<HotGoodsBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_hotgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HotGoodsBean.DataBeanX.DataBean bean = (HotGoodsBean.DataBeanX.DataBean) data;
        ImageView img_hotgoods = (ImageView) vh.getViewById(R.id.img_hotgoods);
        TextView hotgoods_name = (TextView) vh.getViewById(R.id.hotgoods_name);
        TextView pri_hotgoods = (TextView) vh.getViewById(R.id.pri_hotgoods);
        Glide.with(context).load(bean.getList_pic_url()).into(img_hotgoods);
        hotgoods_name.setText(bean.getName());
        pri_hotgoods.setText("￥"+bean.getRetail_price());

        String s = pri_hotgoods.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.RED),0,s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        pri_hotgoods.setText(builder);
    }
}

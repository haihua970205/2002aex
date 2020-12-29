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
import com.example.test_kao.bean.HomeBean;

import java.util.List;

public class NewAdapter extends BaseAdapter {
    private Context context;
    public NewAdapter(Context context, List<HomeBean.DataBean.NewGoodsListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_new;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.NewGoodsListBean bean = (HomeBean.DataBean.NewGoodsListBean) data;
        ImageView img_new = (ImageView) vh.getViewById(R.id.img_new);
        TextView new_name = (TextView) vh.getViewById(R.id.new_name);
        TextView pri = (TextView) vh.getViewById(R.id.pri);

        Glide.with(context).load(bean.getList_pic_url()).into(img_new);
        new_name.setText(bean.getName());
        pri.setText("￥"+bean.getRetail_price());
        String s = pri.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.RED),0,s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        pri.setText(builder);
    }
}

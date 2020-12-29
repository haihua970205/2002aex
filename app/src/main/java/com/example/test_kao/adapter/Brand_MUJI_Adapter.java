package com.example.test_kao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.BrandIdBean;

import java.util.List;

public class Brand_MUJI_Adapter extends BaseAdapter {
    public Brand_MUJI_Adapter(Context context, List<BrandIdBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_muji;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandIdBean.DataBeanX.DataBean bean = (BrandIdBean.DataBeanX.DataBean) data;
        ImageView img_brand_muji = (ImageView) vh.getViewById(R.id.img_brand_muji);
        TextView tv_muji_name = (TextView) vh.getViewById(R.id.tv_muji_name);
        TextView tv_muji_pri = (TextView) vh.getViewById(R.id.tv_muji_pri);

        Glide.with(context).load(bean.getList_pic_url()).into(img_brand_muji);
        tv_muji_name.setText(bean.getName());
        tv_muji_pri.setText("￥"+bean.getRetail_price());
        String s = tv_muji_pri.getText().toString();
        //富文本
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),0,
                s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv_muji_pri.setText(builder);
    }
}

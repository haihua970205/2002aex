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

public class HotAdapter extends BaseAdapter {
    private Context context;
    public HotAdapter(Context context, List<HomeBean.DataBean.HotGoodsListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_hot;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.HotGoodsListBean bean = (HomeBean.DataBean.HotGoodsListBean) data;
        ImageView img_hot = (ImageView) vh.getViewById(R.id.img_hot);
        TextView hot_name = (TextView) vh.getViewById(R.id.hot_name);
        TextView pri_hot = (TextView) vh.getViewById(R.id.pri_hot);
        TextView brief = (TextView) vh.getViewById(R.id.brief);

        Glide.with(context).load(bean.getList_pic_url()).into(img_hot);
        hot_name.setText(bean.getName());
        brief.setText(bean.getGoods_brief());
        pri_hot.setText("￥"+bean.getRetail_price());
        String s = pri_hot.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.RED),0,s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        pri_hot.setText(builder);
    }
}

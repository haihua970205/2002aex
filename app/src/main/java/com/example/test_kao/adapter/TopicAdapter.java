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

public class TopicAdapter extends BaseAdapter {
    private Context context;
    public TopicAdapter(Context context, List<HomeBean.DataBean.TopicListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_topic;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.TopicListBean bean = (HomeBean.DataBean.TopicListBean) data;
        ImageView img_topic = (ImageView) vh.getViewById(R.id.img_topic);
        TextView title = (TextView) vh.getViewById(R.id.title);
        TextView price_info = (TextView) vh.getViewById(R.id.price_info);
        TextView subtitle = (TextView) vh.getViewById(R.id.subtitle);

        Glide.with(context).load(bean.getItem_pic_url()).into(img_topic);
        title.setText(bean.getTitle());
        subtitle.setText(bean.getSubtitle());
        price_info.setText("￥"+bean.getPrice_info()+"元起");
        String s = price_info.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),0,
                s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        price_info.setText(builder);
    }
}

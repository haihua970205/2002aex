package com.example.test_kao.adapter.topic;

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
import com.example.test_kao.bean.topic.TopicBean;

import java.util.List;

public class Topic_frag_Adapter extends BaseAdapter {
    private Context context;
    public Topic_frag_Adapter(Context context, List<TopicBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_topic_frag;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicBean.DataBeanX.DataBean bean = (TopicBean.DataBeanX.DataBean) data;
        ImageView img_topic_frag = (ImageView) vh.getViewById(R.id.img_topic_frag);
        TextView title_frag = (TextView) vh.getViewById(R.id.title_frag);
        TextView price_info_frag = (TextView) vh.getViewById(R.id.price_info_frag);
        TextView title_sub = (TextView) vh.getViewById(R.id.title_sub);

        Glide.with(context).load(bean.getScene_pic_url()).into(img_topic_frag);
        title_frag.setText(bean.getTitle());
        title_sub.setText(bean.getSubtitle());
        price_info_frag.setText(bean.getPrice_info()+"元起");
    }
}

package com.example.test_kao.adapter.topic;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.topic.TopicRelated;
import com.example.test_kao.utils.TxtUtils;

import java.util.List;

public class TopicRelatedAdapter extends BaseAdapter {
    public TopicRelatedAdapter(Context context, List<TopicRelated.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_related;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicRelated.DataBean bean = (TopicRelated.DataBean) data;
        ImageView img_topic_relate = (ImageView) vh.getViewById(R.id.img_topic_relate);
        TextView txt_topic_relate = (TextView) vh.getViewById(R.id.txt_topic_relate);
        Glide.with(context).load(bean.getScene_pic_url()).into(img_topic_relate);
        TxtUtils.setTextView(txt_topic_relate,bean.getTitle());
    }
}

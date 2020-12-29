package com.example.test_kao.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.TalkBean;
import com.example.test_kao.utils.TxtUtils;

import java.util.List;

public class Car_TalkAdapter extends BaseAdapter {
    public Car_TalkAdapter(Context context, List<TalkBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.car_item_talk;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TalkBean.DataBeanX.DataBean bean = (TalkBean.DataBeanX.DataBean) data;
        ImageView img_can = (ImageView) vh.getViewById(R.id.img_can);
        ImageView img_canAll = (ImageView) vh.getViewById(R.id.img_canAll);
        TextView canAll_name = (TextView) vh.getViewById(R.id.canAll_name);
        TextView canAll_date = (TextView) vh.getViewById(R.id.canAll_date);
        TextView canAll_content = (TextView) vh.getViewById(R.id.canAll_content);
        TxtUtils.setTextView(canAll_name,bean.getUser_info().getNickname()+"");
        TxtUtils.setTextView(canAll_date,bean.getAdd_time());
        TxtUtils.setTextView(canAll_content,bean.getContent());
    }
}

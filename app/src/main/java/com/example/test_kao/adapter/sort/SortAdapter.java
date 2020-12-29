package com.example.test_kao.adapter.sort;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.sort.SortBean;

import java.util.List;

public class SortAdapter extends BaseAdapter {
    private Context context;
    public SortAdapter(Context context, List<SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.rlv_item_sort;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean =
                (SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView txt = (TextView) vh.getViewById(R.id.txt);
        Glide.with(context).load(bean.getWap_banner_url()).into(iv);
        txt.setText(bean.getName());
    }
}

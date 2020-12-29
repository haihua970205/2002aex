package com.example.test_kao.adapter.shop;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.shop.CheckCarBean;
import com.example.test_kao.utils.ImageLoader;
import com.example.test_kao.utils.TxtUtils;

import java.util.List;

public class CarInfoAdapter extends BaseAdapter {
    public CarInfoAdapter(Context context, List<CheckCarBean.DataBean.CheckedGoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_carinfo;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        CheckCarBean.DataBean.CheckedGoodsListBean bean = (CheckCarBean.DataBean.CheckedGoodsListBean) data;
        ImageView imgcarinfo = (ImageView) vh.getViewById(R.id.img_carinfo);
        TextView txt_carinfo_name = (TextView) vh.getViewById(R.id.txt_carinfo_name);
        TextView txt_carinfo_count = (TextView) vh.getViewById(R.id.txt_carinfo_count);
        TextView txt_carinfo_price = (TextView) vh.getViewById(R.id.txt_carinfo_price);

        ImageLoader.loadImage(bean.getList_pic_url(),imgcarinfo);
        TxtUtils.setTextView(txt_carinfo_name,bean.getGoods_name());
        txt_carinfo_price.setText("￥"+bean.getRetail_price());
        txt_carinfo_count.setText("x"+bean.getNumber());
    }
}

package com.example.test_kao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.utils.TxtUtils;

import java.util.List;



public class GoodsBeanAdapter extends BaseAdapter {


    public GoodsBeanAdapter(Context context, List<InfoBean.DataBeanX.GoodsListBean> data) {
        super( context, data );
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.fragment_current;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        InfoBean.DataBeanX.GoodsListBean goodsListBean= (InfoBean.DataBeanX.GoodsListBean) data;

        ImageView goods_image = (ImageView) vh.getViewById( R.id.goods_image );
        TextView googs_name = (TextView) vh.getViewById( R.id.tv_goods_name );
        TextView googs_retail_price = (TextView) vh.getViewById( R.id.tv_goods_retail_price );

        Glide.with( context ).load( goodsListBean.getList_pic_url() ).into( goods_image );
        TxtUtils.setTextView( googs_name,goodsListBean.getName() );
        TxtUtils.setTextView( googs_retail_price,"￥"+goodsListBean.getRetail_price() );
        String s = googs_retail_price.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),0,
                s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        googs_retail_price.setText(builder);

    }
}

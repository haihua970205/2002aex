package com.example.test_kao.adapter.me;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.ui.me.ShoppingBean;
import com.example.test_kao.utils.TxtUtils;

import java.util.List;

public class FavoritesAdapter extends BaseAdapter {
    public FavoritesAdapter(Context context, List<ShoppingBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.itm_favorites;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ShoppingBean bean = (ShoppingBean) data;
        ImageView img_favor = (ImageView) vh.getViewById(R.id.img_favor);
        TextView name_favor = (TextView) vh.getViewById(R.id.name_favor);
        TextView price_favor = (TextView) vh.getViewById(R.id.price_favor);

        Glide.with(context).load(bean.getPic()).into(img_favor);
        TxtUtils.setTextView(name_favor,bean.getName());
        price_favor.setText("￥"+bean.getPice());
    }
}

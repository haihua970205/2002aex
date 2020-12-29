package com.example.test_kao.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;

import java.util.List;

public class DetailAdapter extends BaseAdapter {
    public DetailAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_detail;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String str = (String) data;
        ImageView img_detail = (ImageView) vh.getViewById(R.id.img_detail);
        Glide.with(context).load(str).into(img_detail);
    }
}

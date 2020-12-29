package com.example.test_kao.adapter.topic;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;

import java.util.List;

public class RlvDeToAdapter extends BaseAdapter {
    public RlvDeToAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.rlv_reto;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String str = (String) data;
        Log.e("111", "bindData: "+str );
        ImageView img_deta_reto = (ImageView) vh.getViewById(R.id.img_deta_reto);
        Glide.with(context).load(str).into(img_deta_reto);
    }
}

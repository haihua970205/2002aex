package com.example.test_kao.adapter.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.shop.ShopBean;
import com.example.test_kao.ui.home.shop.CarActivity;
import com.example.test_kao.utils.ImageLoader;
import com.example.test_kao.utils.TxtUtils;
import com.example.test_kao.widget.NumberSelect;

import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private boolean isEdit; //是否编辑
    private UpdateItem updateItem;
    private Activity activity;
    public ShopAdapter(Context context, List data, Activity activity) {
        super(context, data, activity);
        this.activity = activity;
    }

    public void setUpdateItem(UpdateItem item) {
        this.updateItem = item;
    }

    public void setEditState(boolean bool) {
        isEdit = bool;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_shop_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ShopBean.DataBean.CartListBean bean = (ShopBean.DataBean.CartListBean) data;
        CheckBox checkBox = (CheckBox) vh.getViewById(R.id.checkbox);
        ImageView imgItem = (ImageView) vh.getViewById(R.id.img_item);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_price);
        TextView txtNumber = (TextView) vh.getViewById(R.id.txt_number);
        TextView txtEditTitle = (TextView) vh.getViewById(R.id.txt_edit_title);
        NumberSelect numberSelect = (NumberSelect) vh.getViewById(R.id.layout_change);

        txtName.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        txtNumber.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        txtEditTitle.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        numberSelect.setVisibility(isEdit ? View.VISIBLE : View.GONE);

        // 设置选中状态
        checkBox.setChecked(isEdit ? bean.selectEdit : bean.selectOrder);
        Glide.with(imgItem).load(bean.getList_pic_url()).into(imgItem);
//        ImageLoader.loadImage(data.getList_pic_url(),imgItem);
        txtName.setText(bean.getGoods_name());
        txtPrice.setText("￥" + bean.getRetail_price());
        txtNumber.setText(String.valueOf(bean.getNumber()));
        numberSelect.addPage(R.layout.layout_number_change);
        numberSelect.setNumber(bean.getNumber());
        numberSelect.addChangeNumber(new NumberSelect.ChangeNumber() {
            @Override
            public void change(int number) {
                //修改本地数据得值
                bean.setNumber(number);
                if (updateItem != null) {
                    updateItem.updateItem(bean);
                }
            }
        });

        checkBox.setTag(bean.getId());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (iItemViewClick != null) {
                    int id = (int) buttonView.getTag();
                    iItemViewClick.itemViewClick(id, isChecked);
                }
            }
        });



       /* if(isEdit){
            txtName.setVisibility(View.GONE);
            txtNumber.setVisibility(View.GONE);
            txtEditTitle.setVisibility(View.VISIBLE);
            numberSelect.setVisibility(View.VISIBLE);
        }else{
            txtName.setVisibility(View.VISIBLE);
            txtNumber.setVisibility(View.VISIBLE);
            txtEditTitle.setVisibility(View.GONE);
            numberSelect.setVisibility(View.GONE);
        }*/
    }

    public interface UpdateItem{
        void updateItem(ShopBean.DataBean.CartListBean cartListBean);
    }
}

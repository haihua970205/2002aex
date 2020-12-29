package com.example.test_kao.ui.shop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kao.R;
import com.example.test_kao.adapter.shop.ShopAdapter;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.bean.shop.DeleteShopBean;
import com.example.test_kao.bean.shop.ShopBean;
import com.example.test_kao.bean.shop.UpdateShopBean;
import com.example.test_kao.interfaces.shop.IShop;
import com.example.test_kao.presenter.shop.ShopPresenter;
import com.example.test_kao.ui.home.shop.CarActivity;
import com.example.test_kao.ui.login.LoginActivity;
import com.example.test_kao.utils.SpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class ShopFrag extends BaseFragment<ShopPresenter> implements IShop.View , View.OnClickListener {

    @BindView(R.id.recy_good)
    RecyclerView recyGood;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.txt_totalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.txt_submit)
    TextView txtSubmit;
    private ShopBean shop = new ShopBean();
    private boolean isEdit; //是否是编辑状态
    private ShopAdapter shopAdapter;
    private ArrayList<ShopBean.DataBean.CartListBean> list;

    @Override
    public int getLayout() {
        return R.layout.shop_frag;
    }

    @Override
    public void initView() {
        //全选的监听
        checkboxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean bool = checkboxAll.isChecked();
                if(isEdit){
                    //编辑
                    updateGoodSelectStateEdit(bool);
                }else{
                    //下单
                    updateGoodSelectStateOrder(bool);
                }
            }
        });

        txtEdit.setOnClickListener(this);
        txtSubmit.setOnClickListener(this);


    }
    @Override
    public void initData() {
        list = new ArrayList<>();
        shopAdapter = new ShopAdapter(getActivity(), list,getActivity());
        recyGood.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyGood.setAdapter(shopAdapter);

        String token = SpUtils.getInstance().getString("token");
        if(!TextUtils.isEmpty(token)) {
            persenter.getCarList();
        }

        // 监听条目元素点击的时候的接口回调
        shopAdapter.addItemViewClick(new BaseAdapter.IItemViewClick() {
            @Override
            public void itemViewClick(int id, Object data) {
                for(ShopBean.DataBean.CartListBean item:shop.getData().getCartList()){
                    if(item.getId() == id){
                        if(!isEdit){
                            item.selectOrder = (boolean) data;
                        }else{
                            item.selectEdit = (boolean) data;
                        }
                        break;
                    }
                }
                boolean isSelectAll;
                if(!isEdit){
                    isSelectAll = totalSelectOrder();
                }else{
                    isSelectAll = totalSelectEdit();
                }
                checkboxAll.setChecked(isSelectAll);
            }
        });

        //监听编辑状态下item的变化
        shopAdapter.setUpdateItem(new ShopAdapter.UpdateItem() {
            @Override
            public void updateItem(ShopBean.DataBean.CartListBean cartListBean) {
                HashMap<String, String> map = new HashMap<>();
                map.put("goodsId",String.valueOf(cartListBean.getGoods_id()));
                map.put("productId",String.valueOf(cartListBean.getProduct_id()));
                map.put("id",String.valueOf(cartListBean.getId()));
                map.put("number",String.valueOf(cartListBean.getNumber()));
                //执行更新
                persenter.updateCar(map);
                totalSelectEdit();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 300){
            persenter.getCarList();
        }

    }

    @Override
    public ShopPresenter createPersenter() {
        return new ShopPresenter(this);
    }


    @Override
    public void shopResult(ShopBean shopBean) {
        this.shop = shopBean;
        List<ShopBean.DataBean.CartListBean> cartList = shopBean.getData().getCartList();
        list.clear();
        list.addAll(cartList);
        shopAdapter.notifyDataSetChanged();
    }

    //更新之后返回
    @Override
    public void updateResult(UpdateShopBean updateShopBean) {
        Log.i("TAG",updateShopBean.toString());

        for(UpdateShopBean.DataBean.CartListBean item:updateShopBean.getData().getCartList()){
            updateCartListBeanNumberById(item.getId(),item.getNumber());
        }
        //更新商品的总数和总价
        shop.getData().getCartTotal().setGoodsCount(updateShopBean.getData().getCartTotal().getGoodsCount());
        shop.getData().getCartTotal().setGoodsAmount(updateShopBean.getData().getCartTotal().getGoodsAmount());
        shopAdapter.notifyDataSetChanged();
        totalSelectEdit();
    }

    //刷新购物车列表的数据
    private void updateCartListBeanNumberById(int carId,int number){
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.getId() == carId){
                item.setNumber(number);
                break;
            }
        }
    }

    //删除
    @Override
    public void deleteResult(DeleteShopBean deleteShopBean) {

        Log.i("TAG","deleteCar:"+deleteShopBean.toString());
        //通过购物车返回的最新数据，同步本地列表中的数据
        int index,lg=list.size();
        for(index=0;index<lg;index++){
            ShopBean.DataBean.CartListBean item = list.get(index);
            boolean bool = deleteCarListById(deleteShopBean.getData().getCartList(),item.getId());
            Log.i("TAG","delete bool:"+bool +" item:"+item.getId());
            if(bool){
                list.remove(index);
                index--;
                lg--;
            }
        }
        shopAdapter.notifyDataSetChanged();
        totalSelectEdit();
    }

    //判断当前的本地列表的购物车列表数据是否在返回的最新列表中存在`
    private boolean deleteCarListById(List<DeleteShopBean.DataBean.CartListBean> list ,int carId){
        for(DeleteShopBean.DataBean.CartListBean item:list){
            if(item.getId() == carId){
                return false;
            }
        }
        return true;
    }

    //下单状态下的数据刷新
    private void updateGoodSelectStateOrder(boolean bool){
        for(ShopBean.DataBean.CartListBean item:list){
            item.selectOrder = bool;
        }
        //计算总数和价格
        totalSelectOrder();
        // 更新列表条目的选中状态
        shopAdapter.notifyDataSetChanged();
    }


    //编辑状态下的数据刷新
    private void updateGoodSelectStateEdit(boolean bool){
        for(ShopBean.DataBean.CartListBean item:list){
            item.selectEdit = bool;
        }
        totalSelectOrder();
        shopAdapter.notifyDataSetChanged();
    }

    //下单状态下的总数和价格的计算
    private boolean totalSelectOrder(){
        int num = 0;
        int totalPrice = 0;
        boolean isSelectAll = true;
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.selectOrder){
                num += item.getNumber();
                totalPrice += item.getNumber()*item.getRetail_price();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        checkboxAll.setText(strAll);
        txtTotalPrice.setText("￥"+totalPrice);
        Log.i("TAG","num: "+num+"price："+totalPrice);
        return isSelectAll;
    }


    //编辑状态下的总数和价格的计算
    private boolean totalSelectEdit(){
        int num = 0;
        boolean isSelectAll = true;
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.selectEdit){
                num += item.getNumber();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        checkboxAll.setText(strAll);
        return isSelectAll;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_edit:
                changeEdit();
                break;
            case R.id.txt_submit:
                submit();
                break;
        }
    }


    //编辑和完成的状态
    private void changeEdit() {
        if("编辑".equals(txtEdit.getText().toString())){
            txtEdit.setText("完成");
            txtSubmit.setText("删除所选");
            isEdit = true;
            txtTotalPrice.setVisibility(View.GONE);
        }else if("完成".equals(txtEdit.getText().toString())){
            txtEdit.setText("编辑");
            txtSubmit.setText("下单");
            isEdit = false;
            updateGoodSelectStateEdit(false);
            txtTotalPrice.setVisibility(View.VISIBLE);
        }
        shopAdapter.setEditState(isEdit);
        shopAdapter.notifyDataSetChanged();
    }

    //下单和删除所选
    private void submit() {
        if("下单".equals(txtSubmit.getText().toString())){
            //下单
            Intent intent = new Intent(getActivity(), CarInfoActivity.class);
            startActivity(intent);
        }else if("删除所选".equals(txtSubmit.getText().toString())){
            //删除购物车所选数据
            deleteCar();
        }
    }

    //删除选中的商品数据
    private void deleteCar(){
        StringBuilder sb = new StringBuilder();
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.selectEdit){
                sb.append(item.getProduct_id());
                sb.append(",");
            }
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        Log.i("TAG",sb.toString());
        persenter.deleteCar(sb.toString());
    }
}

package com.example.test_kao.ui.home.hotgoods;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.adapter.HotGoodsAdapter;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.bean.HotGoodsBean;
import com.example.test_kao.bean.HotTopBean;
import com.example.test_kao.interfaces.IHotGoods;
import com.example.test_kao.presenter.HotGoodsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HotGoodsActivity extends BaseActivity<HotGoodsPresenter> implements IHotGoods.View {

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "categoryId";

    @BindView(R.id.img_hotgood)
    ImageView imgHotgood;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.layout_info)
    ConstraintLayout layoutInfo;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.layout_price)
    LinearLayout layoutPrice;
    @BindView(R.id.txt_sort)
    TextView txtSort;
    @BindView(R.id.layout_sort)
    ConstraintLayout layoutSort;
    @BindView(R.id.recy_goodList)
    RecyclerView recyGoodList;
    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order;
    private String sort;
    private int categoryId;

    @Override
    protected int getLayout() {
        return R.layout.activity_hot_goods;
    }

    @Override
    protected HotGoodsPresenter createPrenter() {
        return new HotGoodsPresenter(this);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        layoutPrice.setTag(0);
        txtAll.setTextColor(Color.parseColor(HotGoodsActivity.this.getString(R.color.red)));
    }

    @Override
    protected void initData() {
        presenter.getHotTop();
        presenter.getHotGoods(getParam());
    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.layout_price,R.id.txt_all,R.id.txt_sort})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.layout_price:
                int tag = (int) layoutPrice.getTag();
                if(tag == 0){
                    resetPriceState();
                    priceStateUp();
                    layoutPrice.setTag(1);
                    order = ASC;
                }else if(tag == 1){
                    resetPriceState();
                    priceStateDown();
                    layoutPrice.setTag(0);
                    order = DESC;
                }
                sort = PRICE;
                presenter.getHotGoods(getParam());
                break;
            case R.id.txt_all:
                resetPriceState();
                txtAll.setTextColor(Color.parseColor(HotGoodsActivity.this.getString(R.color.red)));
                sort = DEFAULT;
                categoryId = 0;
                presenter.getHotGoods(getParam());
                break;
            case R.id.txt_sort:
                resetPriceState();
                txtSort.setTextColor(Color.parseColor(HotGoodsActivity.this.getString(R.color.red)));
                sort = CATEGORY;
                presenter.getHotGoods(getParam());
                break;
        }
    }


    /**
     * 组装当前的接口参数
     * @return
     */
    private HashMap<String,String> getParam(){
        HashMap<String,String> map = new HashMap<>();
        map.put("isNew",String.valueOf(isNew));
        map.put("page",String.valueOf(page));
        map.put("size",String.valueOf(size));
        map.put("order",order);
        map.put("sort",sort);
        map.put("categoryId",String.valueOf(categoryId));
        return map;
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private void priceStateUp(){
        imgArrowUp.setImageResource(R.mipmap.upx);
        imgArrowDown.setImageResource(R.mipmap.downs);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private void priceStateDown(){
        imgArrowUp.setImageResource(R.mipmap.ups);
        imgArrowDown.setImageResource(R.mipmap.downx);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState(){
        imgArrowUp.setImageResource(R.mipmap.ups);
        imgArrowDown.setImageResource(R.mipmap.downs);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.black)));
        txtAll.setTextColor(Color.parseColor(getString(R.color.black)));
        txtSort.setTextColor(Color.parseColor(getString(R.color.black)));
        layoutPrice.setTag(0);
    }

    @Override
    public void getResult(HotTopBean result) {
        HotTopBean.DataBean.BannerInfoBean info = result.getData().getBannerInfo();
        Glide.with(this).load(info.getImg_url()).into(imgHotgood);
        txtInfo.setText(info.getName());
    }

    @Override
    public void getResult(HotGoodsBean result) {

        List<HotGoodsBean.DataBeanX.DataBean> data = result.getData().getData();

        recyGoodList.setLayoutManager(new GridLayoutManager(this,2));
        HotGoodsAdapter hotGoodsAdapter = new HotGoodsAdapter(this, data);
        recyGoodList.setAdapter(hotGoodsAdapter);
        hotGoodsAdapter.notifyDataSetChanged();

        //获得分类的数据
        if(sort == CATEGORY){
            List<HotGoodsBean.DataBeanX.FilterCategoryBean> filterCategory = result.getData().getFilterCategory();
            getFilter(filterCategory);
            sort = DEFAULT;
        }
    }

    //获得数据并弹出pw
    private void getFilter(List<HotGoodsBean.DataBeanX.FilterCategoryBean> filterCategory) {
        View inflate = LayoutInflater.from(HotGoodsActivity.this).inflate(R.layout.pw, null);
        PopupWindow pw = new PopupWindow(inflate, -1, 80);
        LinearLayout ll_pw = inflate.findViewById(R.id.ll_pw);

        if(filterCategory.size()>0){
            for(int i=0;i<filterCategory.size();i++){
                TextView textView = buildLabel(filterCategory.get(i).getName());
                ll_pw.addView(textView);
                int finalI = i;

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("isNew",String.valueOf(1));
                        map.put("categoryId",String.valueOf(filterCategory.get(finalI).getId()));
                        presenter.getHotGoods(map);
                        pw.dismiss();
                    }
                });
            }

        }
        //显示在控件的下面
        pw.showAsDropDown(layoutSort,0,0);
        pw.setOutsideTouchable(true);
    }
    private TextView buildLabel(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1); // , 1是可选写的
        lp.setMargins(10, 20, 30, 20);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.ring_txt);
        return textView;
    }
}

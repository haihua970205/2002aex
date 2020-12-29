package com.example.test_kao.ui.shop;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kao.R;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.shop.AddressBean;
import com.example.test_kao.bean.shop.CheckCarBean;
import com.example.test_kao.bean.shop.ProvinceBean;
import com.example.test_kao.interfaces.shop.ICarInfo;
import com.example.test_kao.presenter.shop.CarInfoPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddProvinceActivity extends BaseActivity<CarInfoPresenter> implements ICarInfo.View {
    @BindView(R.id.name_province)
    EditText nameProvince;
    @BindView(R.id.name_phone)
    EditText namePhone;
    @BindView(R.id.address_three)
    EditText addressThree;
    @BindView(R.id.detail_address)
    EditText detailAddress;
    @BindView(R.id.base_address)
    TextView baseAddress;
    @BindView(R.id.address_ll)
    LinearLayout addressLl;
    private ArrayList<ProvinceBean.DataBean> lists;

    // recyclerView 选中Item 的颜色
    private int defaultSelectedColor = Color.parseColor("#AB2B2B");
    // recyclerView 未选中Item 的颜色
    private int defaultUnSelectedColor = Color.parseColor("#262626");
    // 确定字体不可以点击时候的颜色
    private int defaultSureUnClickColor = Color.parseColor("#7F7F7F");
    // 确定字体可以点击时候的颜色
    private int defaultSureCanClickColor = Color.parseColor("#AB2B2B");


    private ProvinceBean.DataBean mSallProvince; //选中的省
    private ProvinceBean.DataBean mSallCity; //选中的市
    private ProvinceBean.DataBean mSallCounty; //选中的区

    private int mSProvicePosition = 0; //选中 省份 位置
    private int mSCityPosition = 0;//选中 城市  位置
    private int mSCountyPosition = 0;//选中 区县  位置
    private AddressCityAdpter addressCityAdpter;
    private PopupWindow pw;

    int id = 1;//第一个Id开始
    private RecyclerView rlv_address1;
    private TabLayout address_tab;
    private Button btn_ok;

    @Override
    protected int getLayout() {
        return R.layout.activity_province;
    }

    @Override
    protected CarInfoPresenter createPrenter() {
        return new CarInfoPresenter(this);
    }

    @Override
    protected void initView() {
        lists = new ArrayList<>();
        //输入框的点击监听
        addressThree.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //弹出pw
                addCity();
            }
        });
    }

    @Override
    protected void initData() {
//        presenter.getProvince(1);
    }

    //TODO 获得省市区的地址
    @Override
    public void getResult(ProvinceBean provinceBean) {
        List<ProvinceBean.DataBean> data = provinceBean.getData();
        lists.addAll(data);
        addressCityAdpter.notifyDataSetChanged();
    }

    private void addCity() {
        //创建pw
        View inflate = LayoutInflater.from(AddProvinceActivity.this).inflate(R.layout.addprovince_pw, null);
        pw = new PopupWindow(inflate, -1, 300);
        //先请求出省的数据
        presenter.getProvince(id);

        //找到rlv和适配器放数据
        rlv_address1 = inflate.findViewById(R.id.rlv_address1);
        rlv_address1.setLayoutManager(new LinearLayoutManager(AddProvinceActivity.this));
        addressCityAdpter = new AddressCityAdpter(this, lists);
        rlv_address1.setAdapter(addressCityAdpter);

        //TODO 初始化默认的本地数据  也提供了方法接收外面数据
        rlv_address1.post(new Runnable() {
            @Override
            public void run() {
                presenter.getProvince(id);
            }
        });

        //tab与确定按钮
        initTab(inflate);

//        threeProvinceAdapter.addListClick(new BaseAdapter.IListClick() {
//            @Override
//            public void itemClick(int pos) {
//                String name = lists.get(pos).getName();
//                address_tab.getTabAt(0).setText(name);
//
//                address_tab.getTabAt(1).select();
//                int id = lists.get(pos).getId();
//                Log.e("111", "onTabSelected: "+id);
//                lists.clear();
//
//                presenter.getProvince(id);
//                threeProvinceAdapter.notifyDataSetChanged();
//            }
//        });

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pw.setOutsideTouchable(true);
        pw.showAtLocation(addressLl, Gravity.BOTTOM, 0, 0);
    }

    private void initTab(View inflate) {
        btn_ok = inflate.findViewById(R.id.btn_ok);
        btn_ok.setTextColor(defaultSureUnClickColor);//确定不可以点击时的颜色设置
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取点击事件的id
                int i = v.getId();
                //判断是否相等
                if(i == R.id.btn_ok){
                    //选中的3个值的集合不为空
                    if(mSallProvince != null && mSallCity != null && mSallCounty != null){
                        addressThree.setText(mSallProvince.getName() + " " + mSallCity.getName() + " " + mSallCounty.getName());
                        pw.dismiss();
                    }else{
                        Toast.makeText(AddProvinceActivity.this, "还没选完", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        address_tab = inflate.findViewById(R.id.address_tab);
        //配置tab页
        address_tab.addTab(address_tab.newTab().setText("省份"));
        address_tab.addTab(address_tab.newTab().setText("城市"));
        address_tab.addTab(address_tab.newTab().setText("区县"));

        //tab的点击监听
        address_tab.addOnTabSelectedListener(tabSelectedListener);
    }

    TabLayout.BaseOnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            lists.clear();
            switch (tab.getPosition()){
                case 0 :
                    presenter.getProvince(id);
                    addressCityAdpter.notifyDataSetChanged();
                    // 滚动到选中省的位置
                    rlv_address1.smoothScrollToPosition(mSProvicePosition);
                    break;
                case 1:
                    if(mSallProvince != null){
                        for(ProvinceBean.DataBean item : lists){
                            if(item.getName().equals(mSallProvince.getName())){
                                lists.add(item);
                            }
                        }
                    }else {
                        showToast("请您先选择省份");
                    }
                    addressCityAdpter.notifyDataSetChanged();
                    rlv_address1.smoothScrollToPosition(mSCityPosition);
                    break;
                case 2:
                    // 点到区的时候要判断有没有选择省份与城市
                    if (mSallProvince != null && mSallCity != null) {
                        for (ProvinceBean.DataBean itemBean : lists) {
                            if (itemBean.getName().equals(mSallCounty.getName()))
                                lists.add(itemBean);
                        }
                    } else {
                        showToast("请您先选择省份与城市");
                    }
                    addressCityAdpter.notifyDataSetChanged();
                    // 滚动到这个位置
                    rlv_address1.smoothScrollToPosition(mSCountyPosition);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        lists = null;
    }
    @Override
    public void getResult(CheckCarBean checkCarBean) {

    }

    @Override
    public void getResult(AddressBean addressBean) {

    }


    //内部适配器
    class AddressCityAdpter extends BaseAdapter {

        public AddressCityAdpter(Context context, List Data) {
            super(context, Data);
        }

        @Override
        protected int getLayout(int type) {
            return R.layout.item_threeprovince;
        }

        @Override
        protected void bindData(Object data, VH vh) {

            //获取Tab页的点击
            int tabSelectPosition = address_tab.getSelectedTabPosition();
            ProvinceBean.DataBean bean= (ProvinceBean.DataBean) data;

            TextView province = (TextView) vh.getViewById(R.id.province);
            province.setText(bean.getName());
            province.setTextColor(defaultUnSelectedColor);//未改变选中区市的颜色

            // 设置选中效果的颜色
            switch (tabSelectPosition) {
                case 0:
                    if (bean != null && mSallProvince != null && bean.getName().equals(mSallProvince.getName())) {
                        province.setTextColor(defaultSelectedColor);//改变选中区市的颜色
                    }
                    break;
                case 1:
                    if (bean != null && mSallCity != null &&
                            bean.getName().equals(mSallCity.getName())) {
                        province.setTextColor(defaultSelectedColor);//改变选中城市的颜色
                    }
                    break;
                case 2:
                    if (bean != null &&  mSallCounty != null &&
                            bean.getName().equals(mSallCounty.getName())) {
                        province.setTextColor(defaultSelectedColor);
                    }
                    break;
            }

            // 设置点击条目之后的事件
            province.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 点击 分类别
                    switch (tabSelectPosition) {
                        case 0:
                            //选中的省的对象
                            mSallProvince = bean;
                            // 清空后面两个的数据
                            mSallCity = null;
                            mSallCounty = null;
                            mSCityPosition = 0;
                            mSCountyPosition = 0;
                            // 获得Tab页的 并且配置Tab
                            address_tab.getTabAt(1).setText("城市");
                            address_tab.getTabAt(2).setText("区县");
                            // 设置这个对应的标题
                            address_tab.getTabAt(0).setText(mSallProvince.getName());
                            // 跳到下一个选择
                            address_tab.getTabAt(1).select();
                            // 灰掉确定按钮
                            btn_ok.setTextColor(defaultSureUnClickColor);
                            presenter.getProvince(bean.getId());
                            mSProvicePosition = bean.getId();
                            break;
                        case 1:
                            mSallCity = bean;
                            // 清空后面一个的数据
                            mSallCounty = null;
                            mSCityPosition = 0;
                            address_tab.getTabAt(2).setText("城市");
                            // 设置这个对应的标题
                            address_tab.getTabAt(1).setText(mSallCity.getName());
                            // 跳到下一个选择
                            address_tab.getTabAt(2).select();
                            // 灰掉确定按钮
                            btn_ok.setTextColor(defaultSureUnClickColor);
                            presenter.getProvince(bean.getId());
                            mSCityPosition = bean.getId();
                            break;
                        case 2:
                            mSallCounty = bean;
                            // 没了，选完了，这个时候可以点确定了
                            address_tab.getTabAt(2).setText(mSallCounty.getName());
                            notifyDataSetChanged();
                            // 确定按钮变亮
                            btn_ok.setTextColor(defaultSureCanClickColor);
                            presenter.getProvince(bean.getId());
                            mSCountyPosition = bean.getId();
                            break;
                    }
                }
            });

        }

    }
}

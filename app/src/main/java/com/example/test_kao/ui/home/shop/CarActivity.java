package com.example.test_kao.ui.home.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_kao.MainActivity;
import com.example.test_kao.R;
import com.example.test_kao.adapter.Car_Product_Adapter;
import com.example.test_kao.adapter.DetailAdapter;
import com.example.test_kao.app.MyApp;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.AddShopBean;
import com.example.test_kao.bean.shop.CarBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.bean.TalkBean;
import com.example.test_kao.interfaces.ICar;
import com.example.test_kao.presenter.CarPresenter;
import com.example.test_kao.ui.login.LoginActivity;
import com.example.test_kao.ui.me.Realms;
import com.example.test_kao.ui.me.ShoppingBean;
import com.example.test_kao.utils.SpUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import io.realm.Realm;

public class CarActivity extends BaseActivity<CarPresenter> implements ICar.View, View.OnClickListener {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.car_name)
    TextView carName;
    @BindView(R.id.car_brief)
    TextView carBrief;
    @BindView(R.id.car_retail_price)
    TextView carRetailPrice;
    @BindView(R.id.txt_assess)
    TextView txtAssess;
    //    @BindView(R.id.webView)
//    WebView webView;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.layout_collect)
    FrameLayout layoutCollect;
    @BindView(R.id.img_car)
    ImageView imgCar;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.layout_car)
    FrameLayout layoutCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCar)
    TextView txtAddCar;
    @BindView(R.id.layout_shop)
    ConstraintLayout layoutShop;
    @BindView(R.id.car_linee)
    LinearLayout carLinee;
    @BindView(R.id.ll_iss)
    LinearLayout llIss;
    @BindView(R.id.rlv_product)
    RecyclerView rlvProduct;
    @BindView(R.id.see_all)
    TextView seeAll;
    @BindView(R.id.img_ca)
    ImageView imgCa;
    @BindView(R.id.can_name)
    TextView canName;
    @BindView(R.id.can_date)
    TextView canDate;
    @BindView(R.id.can_content)
    TextView canContent;
    @BindView(R.id.img_canAll)
    ImageView imgCanAll;
    @BindView(R.id.rlv_detail)
    RecyclerView rlvDetail;

    private Button btn_jia;
    private Button btn_jian;
    private TextView tv_shu;
    private int shu;
    private boolean isVisible = false;
    private PopupWindow popupWindow;
    private PopupWindow pw;

    @Override
    protected int getLayout() {
        return R.layout.activity_car;
    }

    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    @Override
    protected CarPresenter createPrenter() {
        return new CarPresenter(this);
    }

    @Override
    protected void initView() {
        imgCar.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        presenter.getCar(id);
        presenter.getTalk();
    }

    @Override
    public void getResult(CarBean carBean) {
        //banner图
        initBanner(carBean.getData().getGallery());

        // 商品信息
        initInfo(carBean.getData().getInfo(),carBean.getData().getProductList());

        //h5 商品详情
        initGoodDetail(carBean.getData().getInfo().getGoods_desc());

        //商品参数
        initattribute(carBean.getData().getAttribute());

        //常见问题
        initIssue(carBean.getData().getIssue());
        //底部数据列表
        initProduct(carBean.getData().getProductList());
    }


    /**
     * 商品详情数据  h5
     *
     * @param webData
     */
    private void initGoodDetail(String webData) {
        getHtmlImgs(webData);
//        String content = h5.replace("word", webData);
//        Log.i("TAG", content);
//        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    private void getHtmlImgs(String content) {
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            //判断图片的格式
            if (end > 0) {
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    list.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    list.add(url);
                } else {
                    return;
                }
            }
        }
        Log.e("111", "initgetImage: "+list);
        rlvDetail.setLayoutManager(new LinearLayoutManager(this));
        DetailAdapter detailAdapter = new DetailAdapter(this, list);
        rlvDetail.setAdapter(detailAdapter);
        detailAdapter.notifyDataSetChanged();

        detailAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(CarActivity.this, BigImageActivity.class);
                MyApp.getMap().put("big", list);
                intent.putExtra("pos", pos);
                startActivity(intent);
            }
        });
    }

    private void initBanner(List<CarBean.DataBeanX.GalleryBean> gallery) {
        banner.setImages(gallery).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                CarBean.DataBeanX.GalleryBean bean = (CarBean.DataBeanX.GalleryBean) path;
                String img_url = bean.getImg_url();
                Glide.with(context).load(img_url).into(imageView);
            }
        }).start();
    }

    private void initInfo(CarBean.DataBeanX.InfoBean info,
                          List<CarBean.DataBeanX.ProductListBean> productList) {
        carName.setText(info.getName());
        carBrief.setText(info.getGoods_brief());
        carRetailPrice.setText("￥" + info.getRetail_price());

        String s = carRetailPrice.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        carRetailPrice.setText(builder);

        if(isVisible == false){
            isVisible = true;
        }else{
            isVisible = false;
        }
        txtAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVisible){
                    initpwVis(info);
                }else{
                    initpwGone(info,productList);
                }
            }
        });

        //点击收藏保存到数据库
        imgCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //异步处理
                Realms.getRealm(CarActivity.this).executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //创建对象
                        ShoppingBean shop = realm.createObject(ShoppingBean.class);
                        //设置值保存
                        shop.setName(info.getName());
                        shop.setPic(info.getList_pic_url());
                        shop.setPice(info.getRetail_price());
                        Toast.makeText(CarActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        imgCollect.setImageResource(R.mipmap.xingh);
                    }
                });
            }
        });
    }

    //添加成功后的弹框
    private void initpwGone(CarBean.DataBeanX.InfoBean info,
                            List<CarBean.DataBeanX.ProductListBean> productList) {
        int id = info.getId();
        String number = tv_shu.getText().toString();
        Log.e("111", "initpwGone: "+number );
        for(int i=0;i<productList.size();i++){
            int proId = productList.get(i).getId();
            presenter.addCar(initMap(id,number,proId));
        }

        popupWindow.dismiss();
        isVisible = true;
    }

    private HashMap initMap(int id, String number, int proId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsId",String.valueOf(id));
        map.put("number",number);
        map.put("productId",String.valueOf(proId));
        return map;
    }

    //显示加入购物车的弹框
    private void initpwVis(CarBean.DataBeanX.InfoBean info) {
        String token = SpUtils.getInstance().getString("token");
        if(!TextUtils.isEmpty(token)){
            //pop
            View join_view = LayoutInflater.from( CarActivity.this ).inflate( R.layout.join_item, null );
            popupWindow = new PopupWindow( join_view, GridLayout.LayoutParams.MATCH_PARENT, 200 );

            popupWindow.showAtLocation( txtAddCar, Gravity.BOTTOM, 0, 70 );

            ImageView image_pop = join_view.findViewById( R.id.image_pop );
            TextView price_pop = join_view.findViewById( R.id.tv_price_pop );
            btn_jia = join_view.findViewById( R.id.btn_jia );
            btn_jian = join_view.findViewById( R.id.btn_jian );
            tv_shu = join_view.findViewById( R.id.btn_shu );
            TextView tv_back = join_view.findViewById( R.id.tv_back );

            Glide.with( CarActivity.this ).load( info.getList_pic_url() ).into( image_pop );
            price_pop.setText( "￥" + info.getRetail_price() );
            shu = 0;

            ClickListener clickListener = new ClickListener();
            btn_jia.setOnClickListener( clickListener );
            btn_jian.setOnClickListener( clickListener );

            tv_back.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }else{
            Intent intent = new Intent(CarActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        isVisible = false;
    }

    //商品规格
    private void initattribute(List<CarBean.DataBeanX.AttributeBean> attribute) {
        for (int i = 0; i < attribute.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.car_item, null);
            TextView text = view.findViewById(R.id.name_c);
            TextView text1 = view.findViewById(R.id.name_cv);
            text.setText(attribute.get(i).getName());
            text1.setText(attribute.get(i).getValue());
            carLinee.addView(view);
        }
    }

    //常见问题
    private void initIssue(List<CarBean.DataBeanX.IssueBean> issue) {
        for (int i = 0; i < issue.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.car_item_issue, null);
            TextView text = view.findViewById(R.id.question);
            TextView text1 = view.findViewById(R.id.answer);
            text.setText(issue.get(i).getQuestion());
            text1.setText(issue.get(i).getAnswer());
            llIss.addView(view);
        }
    }

    private void initProduct(List<CarBean.DataBeanX.ProductListBean> productList) {
        //获取要传的id
        for (int i = 0; i < productList.size(); i++) {
            int goods_id = productList.get(i).getGoods_id();
            presenter.getrelated(goods_id);
        }
    }

    @Override
    public void getResult(RelateBean relateBean) {
        List<RelateBean.DataBean.GoodsListBean> goodsList = relateBean.getData().getGoodsList();
        rlvProduct.setLayoutManager(new GridLayoutManager(this, 2));
        Car_Product_Adapter car_product_adapter = new Car_Product_Adapter(this, goodsList);
        rlvProduct.setAdapter(car_product_adapter);
        car_product_adapter.notifyDataSetChanged();
    }

    @Override
    public void getResult(TalkBean talkBean) {
        List<TalkBean.DataBeanX.DataBean> data = talkBean.getData().getData();

        canName.setText(data.get(0).getUser_info().getNickname() + "");
        canDate.setText(data.get(0).getAdd_time());
        canContent.setText(data.get(0).getContent());

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarActivity.this, CanAllActivity.class);
                MyApp.getMap().put("list", data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getResult(AddShopBean addShopBean) {
        List<AddShopBean.DataBean.CartListBean> cartList = addShopBean.getData().getCartList();
        if(cartList != null){
            View inflate = LayoutInflater.from(this).inflate(R.layout.join_pw_gone, null);
            pw = new PopupWindow(inflate, 200, 200);

            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.alpha = 0.7f;
            pw.showAtLocation(txtAddCar,Gravity.CENTER,0,0);

            //停留2秒关闭
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            WindowManager.LayoutParams attributes = getWindow().getAttributes();
                            attributes.alpha = 1f;
                            getWindow().setAttributes(attributes);
                            pw.dismiss();
                        }
                    });
                }
            },1000,3000);

        }else{
            Toast.makeText(this, "库存不足", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_car:
                Intent intent = new Intent(CarActivity.this,MainActivity.class);
                intent.putExtra("posit",3);
                startActivity(intent);
                break;
        }
    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_jia:
                    shu++;
                    if (shu >= 0) {
                        tv_shu.setText( shu + "" );
                    }

                    break;
                case R.id.btn_jian:
                    shu--;
                    if (shu >= 0) {
                        tv_shu.setText( shu + "" );
                    }
                    break;
            }
        }
    }
}

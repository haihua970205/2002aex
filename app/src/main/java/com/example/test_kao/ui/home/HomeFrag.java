package com.example.test_kao.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.adapter.BrandAdapter;
import com.example.test_kao.adapter.CategoryListAdapter;
import com.example.test_kao.adapter.HotAdapter;
import com.example.test_kao.adapter.NewAdapter;
import com.example.test_kao.adapter.TopicAdapter;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.interfaces.IMain;
import com.example.test_kao.presenter.HomePresenter;
import com.example.test_kao.ui.home.brand.TvBrandActivity;
import com.example.test_kao.ui.home.channel.ChannelActivity;
import com.example.test_kao.ui.home.hotgoods.HotGoodsActivity;
import com.example.test_kao.ui.home.shop.CarActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFrag extends BaseFragment<HomePresenter> implements IMain.View {

//    @BindView(R.id.banners)
//    Banner banners;
    @BindView(R.id.liness)
    LinearLayout liness;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.rlv_brand)
    RecyclerView rlvBrand;
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    @BindView(R.id.rlv_hot)
    RecyclerView rlvHot;
    @BindView(R.id.rlv_topic)
    RecyclerView rlvTopic;
    @BindView(R.id.liner)
    LinearLayout liner;
    @BindView(R.id.hot_goods)
    TextView hotGoods;


    private List<HomeBean.DataBean.BannerBean> ban = new ArrayList<>();

    private BrandAdapter brandAdapter;

    private NewAdapter newAdapter;

    private HotAdapter hotAdapter;

    private TopicAdapter topicAdapter;
    private ArrayList<HomeBean.DataBean.CategoryListBean> cates;
    private Banner banners;
    private List<HomeBean.DataBean.HotGoodsListBean> hots;
    private List<HomeBean.DataBean.BrandListBean> brands;
    private List<HomeBean.DataBean.NewGoodsListBean> news;

    @Override
    public int getLayout() {
        return R.layout.home_frag;
    }

    @Override
    public void initView() {
        banners = getActivity().findViewById(R.id.banners);

        //居家
        liner = getActivity().findViewById(R.id.liner);
        cates = new ArrayList<>();
        //选择
        liness = getActivity().findViewById(R.id.liness);
        //点击品牌直供
        initTvbrand();

        //品牌
        brands = new ArrayList<>();
        brandAdapter = new BrandAdapter(getActivity(), brands);
        rlvBrand.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rlvBrand.setAdapter(brandAdapter);
        //点击新品首发
        initHotGoods();

        //新品
        news = new ArrayList<>();
        newAdapter = new NewAdapter(getActivity(), news);
        rlvNew.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rlvNew.setAdapter(newAdapter);

        //人气
        hots = new ArrayList<>();
        hotAdapter = new HotAdapter(getActivity(), hots);
        rlvHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlvHot.setAdapter(hotAdapter);
    }

    private void initHotGoods() {
        hotGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HotGoodsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTvbrand() {
        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TvBrandActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public HomePresenter createPersenter() {
        return new HomePresenter(this);
    }

    @Override
    public void initData() {
        persenter.getHome();
    }

    @Override
    public void getResult(HomeBean homeBean) {
        //Banner图
        initBanner(homeBean.getData().getBanner());
        //5种选择
        initChannel(homeBean.getData().getChannel());
        //品牌制造商
        initBrand(homeBean.getData().getBrandList());
        //新品首发
        initNew(homeBean.getData().getNewGoodsList());
        //人气推荐
        initHot(homeBean.getData().getHotGoodsList());
        //专题精选
        initTopic(homeBean.getData().getTopicList());
        //居家
        initCategory(homeBean.getData().getCategoryList());
    }


    private void initBanner(List<HomeBean.DataBean.BannerBean> banner) {
        banners.setImages(banner).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean img = (HomeBean.DataBean.BannerBean) path;
                String image_url = img.getImage_url();
                Glide.with(context).load(image_url).into(imageView);
            }
        }).start();
    }

    //动态添加
    private void initChannel(List<HomeBean.DataBean.ChannelBean> channel) {
        for (int i = 0; i < channel.size(); i++) {
            if(getActivity() != null){
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.home_item_channel, null);
                ImageView img_channel = inflate.findViewById(R.id.img_channel);
                TextView tv_channel = inflate.findViewById(R.id.tv_channel);

                tv_channel.setText(channel.get(i).getName());
                Glide.with(getActivity()).load(channel.get(i).getIcon_url()).into(img_channel);
                liness.addView(inflate);
                //权重设置
                inflate.setLayoutParams(new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                inflate.setTag(channel.get(i));
                int finalI = i;
                inflate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //绑定数据
                        String url = ((HomeBean.DataBean.ChannelBean) v.getTag()).getUrl();
                        int i1 = url.indexOf("=");
                        String substring = url.substring(i1 + 1);
                        Intent intent = new Intent(getActivity(), ChannelActivity.class);
                        intent.putExtra("substring", substring);
                        intent.putExtra("name", channel.get(finalI).getName());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    private void initBrand(List<HomeBean.DataBean.BrandListBean> brandList) {
        brands.addAll(brandList);
        brandAdapter.notifyDataSetChanged();
    }

    private void initNew(List<HomeBean.DataBean.NewGoodsListBean> newGoodsList) {
        news.addAll(newGoodsList);
        newAdapter.notifyDataSetChanged();
    }

    private void initHot(List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList) {
        rlvHot.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        hots.addAll(hotGoodsList);
        hotAdapter.notifyDataSetChanged();
    }

    private void initTopic(List<HomeBean.DataBean.TopicListBean> topicList) {
        rlvTopic.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        topicAdapter = new TopicAdapter(getActivity(), topicList);
        rlvTopic.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();
    }

    private void initCategory(List<HomeBean.DataBean.CategoryListBean> categoryList) {
        cates.addAll(categoryList);
        for (int i = 0; i < categoryList.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_item_sss, null);
            TextView text = view.findViewById(R.id.txt_home_title);
            text.setText(categoryList.get(i).getName());

            RecyclerView recy_home = view.findViewById(R.id.recy_home);
            List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList1 = categoryList.get(i).getGoodsList();
            recy_home.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

            CategoryListAdapter categoryListAdapter = new CategoryListAdapter(getActivity(), goodsList1,getActivity());

            recy_home.setAdapter(categoryListAdapter);

            categoryListAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    Intent intent = new Intent(getActivity(), CarActivity.class);
                    intent.putExtra("id", goodsList1.get(pos).getId());
                    startActivity(intent);
                }
            });
            liner.addView(view);
        }
    }
}

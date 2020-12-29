package com.example.test_kao.app;

import com.example.test_kao.bean.AddShopBean;
import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.BrandIdBean;
import com.example.test_kao.bean.Brand_Tv_infoBean;
import com.example.test_kao.bean.me.UserInfoBean;
import com.example.test_kao.bean.shop.ProvinceBean;
import com.example.test_kao.bean.shop.AddressBean;
import com.example.test_kao.bean.shop.CarBean;
import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.bean.HotGoodsBean;
import com.example.test_kao.bean.HotTopBean;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.bean.TalkBean;
import com.example.test_kao.bean.TypeBean;
import com.example.test_kao.bean.login.LoginBean;
import com.example.test_kao.bean.login.RegisterBean;
import com.example.test_kao.bean.shop.CheckCarBean;
import com.example.test_kao.bean.shop.DeleteShopBean;
import com.example.test_kao.bean.shop.ShopBean;
import com.example.test_kao.bean.shop.UpdateShopBean;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.bean.topic.TopicBean;
import com.example.test_kao.bean.topic.TopicRelated;
import com.example.test_kao.bean.topic.TopicdeBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    String BASE_url = "https://cdplay.cn/";

    //首页
    @GET("api/index")
    Flowable<HomeBean>getHome();

    //分类?id=1005000
    @GET("goods/category")
    Flowable<TypeBean>getType(@Query("id") int id);

    //5种选择的详情
    @GET()
    Flowable<InfoBean>getCate(@Url String url);

   // https://cdplay.cn/api/brand/list?page=1&size=1000
    //品牌制造商详情
    @GET("api/brand/list/{p}")
    Flowable<BrandBean>getBrand(@Path("p") int p, @Query("size") int size);

    //https://cdplay.cn/api/brand/detail?id=1001000
    //对应的品牌制造商
    @GET("api/brand/detail")
    Flowable<Brand_Tv_infoBean>getBrandtvinfo(@Query("id") int id);

    //品牌制造商的商品列表
    //https://cdplay.cn/api/goods/list?brandId=1001007&page=1&size=1000
    @GET("api/goods/list")
    Flowable<BrandIdBean>getBrandId(@Query("brandId") int id);

    //新品顶部
    //https://cdplay.cn/api/goods/hot
    @GET("api/goods/hot")
    Flowable<HotTopBean> getHotTop();


    //https://cdplay.cn/api/goods/list?isNew=1&page=1&size =1000&order=asc&sort=default&categoryId=0
    //新品页面的商品列表
    @GET("api/goods/list")
    Flowable<HotGoodsBean>getHotGoods(@QueryMap HashMap<String,String> map);

    //https://cdplay.cn/api/goods/detail?id=1009024
    //商品购买详情页
    @GET("api/goods/detail")
    Flowable<CarBean>getCar(@Query("id") int id);

    //https://cdplay.cn/api/goods/related?id=1155000
    //底部商品列表
    @GET("api/goods/related")
    Flowable<RelateBean>getrelated(@Query("id") int id);

    //https://cdplay.cn/api/comment/list?typeId=1&valueId=314
    //商品评论的内容
    @GET("api/comment/list?typeId=1&valueId=314")
    Flowable<TalkBean>getTalk();

    //https://cdplay.cn/api/catalog/index
    //分类，竖着的导航
    @GET("api/catalog/index")
    Flowable<VTypeBean>getVType();

    //https://cdplay.cn/api/catalog/current?id=1005001
    //右边数据
    @GET("api/catalog/current")
    Flowable<SortBean>getSort(@Query("id") int id);

   //https://cdplay.cn/api/auth/register
    //注册
    @POST("api/auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean>register(@Field("username") String username, @Field("password") String pw);

    //登录
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String pw);


    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddShopBean> addCar(@FieldMap Map<String,String> map);

    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateShopBean> updateCar(@FieldMap Map<String, String> map);

   //删除购物车数据
   @POST("api/cart/delete")
   @FormUrlEncoded
   Flowable<DeleteShopBean> deleteCar(@Field("productIds") String productIds);


    //购物车列表
    @GET("api/cart/index")
    Flowable<ShopBean> getCarList();

    //专题列表
    //https://cdplay.cn/api/topic/list
    @GET("api/topic/list/")
    Flowable<TopicBean>getTopic(@Query("page") int page);

    //https://cdplay.cn/api/topic/detail?id=314
    //专题详情数据
    @GET("api/topic/detail")
    Flowable<TopicdeBean>getTopicde(@Query("id") int id);

    //https://cdplay.cn/api/topic/related?id=314
   //专题底部列表
    @GET("api/topic/related")
    Flowable<TopicRelated>getTopicRela(@Query("id") int id);

    //https://cdplay.cn/api/cart/checkout?addressId=1&couponId=1
    //下单前的信息确认
    @GET("api/cart/checkout?addressId=1&couponId=1")
    Flowable<CheckCarBean>getCheckCarList(@Query("addressId") int addId,@Query("couponId") int coupId);

    //https://cdplay.cn/api/address/list
    //收货地址的列表
    @GET("api/address/list")
    Flowable<AddressBean>getAddress();

    //https://cdplay.cn/api/region/list?parentId=1
    //省市区的数据
    @GET("api/region/list")
    Flowable<ProvinceBean>getProvince(@Query("parentId") int parentId);

   //用户信息更新
   @POST("api/user/updateUserInfo")
   @FormUrlEncoded
   Flowable<UserInfoBean> updateUserInfo(@FieldMap Map<String,String> map);

}

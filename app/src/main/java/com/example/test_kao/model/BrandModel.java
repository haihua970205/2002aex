package com.example.test_kao.model;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.BrandIdBean;
import com.example.test_kao.bean.Brand_Tv_infoBean;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBrand;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class BrandModel extends BaseModel implements IBrand.Model {
    @Override
    public void getBrand(int p, int size, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getBrand(p,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandBean>(callback) {
                    @Override
                    public void onNext(BrandBean brandBean) {
                        callback.Scuess(brandBean);
                    }
                }));
    }

    @Override
    public void getBrandtvinfo(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getBrandtvinfo(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<Brand_Tv_infoBean>(callback) {
                    @Override
                    public void onNext(Brand_Tv_infoBean brand_tv_infoBean) {
                        callback.Scuess(brand_tv_infoBean);
                    }
                }));
    }

    @Override
    public void getBrandId(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getBrandId(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandIdBean>(callback) {
                    @Override
                    public void onNext(BrandIdBean brandIdBean) {
                        callback.Scuess(brandIdBean);
                    }
                }));
    }
}

package com.example.test_kao.model.shop;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.AddShopBean;
import com.example.test_kao.bean.TypeBean;
import com.example.test_kao.bean.shop.DeleteShopBean;
import com.example.test_kao.bean.shop.ShopBean;
import com.example.test_kao.bean.shop.UpdateShopBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.shop.IShop;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

public class ShopModel extends BaseModel implements IShop.Model {
    @Override
    public void getCarList(Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getCarList()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ShopBean>(callback) {
                    @Override
                    public void onNext(ShopBean shopBean) {
                        callback.Scuess(shopBean);
                    }
                }));
    }

    @Override
    public void updateCar(HashMap map, Callback callback) {
        addDisposable((Disposable) HttpManger.getInstance().getApiService().updateCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateShopBean>(callback) {
                    @Override
                    public void onNext(UpdateShopBean updateShopBean) {
                        callback.Scuess(updateShopBean);
                    }
                }));
    }

    @Override
    public void deleteCar(String productIds, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().deleteCar(productIds)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteShopBean>(callback) {
                    @Override
                    public void onNext(DeleteShopBean deleteShopBean) {
                        callback.Scuess(deleteShopBean);
                    }
                }));
    }


}

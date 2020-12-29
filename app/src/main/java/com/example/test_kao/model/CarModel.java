package com.example.test_kao.model;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.AddShopBean;
import com.example.test_kao.bean.shop.CarBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.bean.TalkBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.ICar;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class CarModel extends BaseModel implements ICar.Model {
    @Override
    public void getCar(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getCar(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<CarBean>(callback) {
            @Override
            public void onNext(CarBean carBean) {
                callback.Scuess(carBean);
            }
        }));
    }

    @Override
    public void getrelated(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getrelated(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RelateBean>(callback) {
                    @Override
                    public void onNext(RelateBean relateBean) {
                        callback.Scuess(relateBean);
                    }
                }));
    }

    @Override
    public void getTalk(Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getTalk()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TalkBean>(callback) {
                    @Override
                    public void onNext(TalkBean talkBean) {
                        callback.Scuess(talkBean);
                    }
                }));
    }

    @Override
    public void addCar(HashMap map, Callback callback) {
        addDisposable((Disposable) HttpManger.getInstance().getApiService().addCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddShopBean>(callback) {
                    @Override
                    public void onNext(AddShopBean addShopBean) {
                        callback.Scuess(addShopBean);
                    }
                }));
    }
}

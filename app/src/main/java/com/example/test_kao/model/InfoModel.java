package com.example.test_kao.model;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.TypeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.ICate;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class InfoModel extends BaseModel implements ICate.Model {
    @Override
    public void getType(int type,Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getType(type)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TypeBean>(callback) {
            @Override
            public void onNext(TypeBean typeBean) {
                callback.Scuess(typeBean);
            }
        }));
    }

    @Override
    public void getCate(int id,Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getCate("api/goods/list?categoryId="+id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<InfoBean>(callback) {
                    @Override
                    public void onNext(InfoBean infoBean) {
                        callback.Scuess(infoBean);
                    }
                }));
    }
}

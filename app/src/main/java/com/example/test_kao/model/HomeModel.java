package com.example.test_kao.model;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IMain;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class HomeModel extends BaseModel implements IMain.Model {
    @Override
    public void getHome(Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getHome()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<HomeBean>(callback) {
            @Override
            public void onNext(HomeBean homeBean) {
                callback.Scuess(homeBean);
            }
        }));
    }
}

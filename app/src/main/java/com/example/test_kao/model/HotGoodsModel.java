package com.example.test_kao.model;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.bean.HotGoodsBean;
import com.example.test_kao.bean.HotTopBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IHotGoods;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

public class HotGoodsModel extends BaseModel implements IHotGoods.Model {
    @Override
    public void getHotTop(Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getHotTop()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotTopBean>(callback) {
                    @Override
                    public void onNext(HotTopBean hotTopBean) {
                        callback.Scuess(hotTopBean);
                    }
                }));
    }

    @Override
    public void getHotGoods(HashMap map, Callback callback) {
        addDisposable((Disposable) HttpManger.getInstance().getApiService().getHotGoods(map)
        .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotGoodsBean>(callback) {
                    @Override
                    public void onNext(HotGoodsBean hotGoodsBean) {
                        callback.Scuess(hotGoodsBean);
                    }
                }));
    }
}

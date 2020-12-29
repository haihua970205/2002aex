package com.example.test_kao.model.me;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.me.UserInfoBean;
import com.example.test_kao.bean.shop.CarBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.me.IMe;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

public class UserInfoModel extends BaseModel implements IMe.Model {

    @Override
    public void updateUserInfo(HashMap map,Callback callback) {
        addDisposable((Disposable) HttpManger.getInstance().getApiService().updateUserInfo(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserInfoBean>(callback) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        callback.Scuess(userInfoBean);
                    }
                }));
    }
}

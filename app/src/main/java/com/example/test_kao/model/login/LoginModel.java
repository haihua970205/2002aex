package com.example.test_kao.model.login;


import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.login.LoginBean;
import com.example.test_kao.bean.login.RegisterBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.login.ILogin;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {
    @Override
    public void login(String username,String pw, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().login(username,pw).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                       callback.Scuess(loginBean);
                    }
                }));
    }

    @Override
    public void register(String name, String pwd, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().register(name ,pwd).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(callback) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        callback.Scuess(registerBean);
                    }
                }));
    }
}

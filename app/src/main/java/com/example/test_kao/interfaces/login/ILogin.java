package com.example.test_kao.interfaces.login;

import com.example.test_kao.bean.login.LoginBean;
import com.example.test_kao.bean.login.RegisterBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBaseModel;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.interfaces.IBaseView;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
        void registerReturn(RegisterBean registerBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void login(String username, String pw);
        void register(String name, String pwd);

    }


    interface Model extends IBaseModel {
        void login(String username, String pw, Callback callback);
        void register(String name, String pwd, Callback callback);
    }
}

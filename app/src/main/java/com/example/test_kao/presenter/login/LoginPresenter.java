package com.example.test_kao.presenter.login;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.login.LoginBean;
import com.example.test_kao.bean.login.RegisterBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.login.ILogin;
import com.example.test_kao.model.login.LoginModel;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {
    ILogin.View view;
    ILogin.Model model;
    public LoginPresenter(ILogin.View view){
        this.view = view;
        model = new LoginModel();
    }
    @Override
    public void login(String username,String pw) {
        model.login(username, pw, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.loginReturn((LoginBean) o);
                }
            }

            @Override
            public void Faile(String msg) {
                if(view != null){
                    view.showToast(msg);
                }
            }
        });
    }

    @Override
    public void register(String name, String pwd) {
        model.register(name, pwd, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.registerReturn((RegisterBean) o);
                }
            }

            @Override
            public void Faile(String msg) {
                if(view != null){
                    view.showToast(msg);
                }
            }
        });
    }
}

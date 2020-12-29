package com.example.test_kao.presenter.me;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.me.UserInfoBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.me.IMe;
import com.example.test_kao.model.me.UserInfoModel;
import com.example.test_kao.ui.me.UserInfoDetailActivity;

import java.util.HashMap;

public class UserInfoPresenter extends BasePresenter<IMe.View> implements IMe.Presenter{
    IMe.View view;
    IMe.Model model;
    public UserInfoPresenter(IMe.View view) {
        this.view = view;
        model = new UserInfoModel();
    }


    @Override
    public void updateUserInfo(HashMap map) {
        model.updateUserInfo(map,new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((UserInfoBean) o);
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

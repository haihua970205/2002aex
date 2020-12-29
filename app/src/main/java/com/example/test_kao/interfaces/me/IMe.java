package com.example.test_kao.interfaces.me;

import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.bean.me.UserInfoBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBaseModel;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.interfaces.IBaseView;
import com.example.test_kao.interfaces.IMain;

import java.util.HashMap;

public interface IMe {
    interface View extends IBaseView {
        void getResult(UserInfoBean userInfoBean);
    }
    interface Presenter extends IBasePresenter<IMe.View> {
        void updateUserInfo(HashMap map);
    }
    interface Model extends IBaseModel {
        void updateUserInfo(HashMap map,Callback callback);
    }
}

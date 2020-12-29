package com.example.test_kao.interfaces;

import com.example.test_kao.bean.HomeBean;

public interface IMain {
    interface View extends IBaseView{
        void getResult(HomeBean homeBean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getHome();
    }
    interface Model extends IBaseModel{
        void getHome(Callback callback);
    }
}

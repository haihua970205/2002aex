package com.example.test_kao.interfaces;

import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.TypeBean;

public interface ICate {
    interface View extends IBaseView{
        void getResult(TypeBean typeBean);
        void getResult(InfoBean infoBean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getType(int type);
        void getCate(int id);
    }
    interface Model extends IBaseModel{
        void getType(int type,Callback callback);
        void getCate(int id,Callback callback);
    }
}

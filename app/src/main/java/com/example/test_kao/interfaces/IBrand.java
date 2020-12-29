package com.example.test_kao.interfaces;

import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.BrandIdBean;
import com.example.test_kao.bean.Brand_Tv_infoBean;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.TypeBean;

public interface IBrand {
    interface View extends IBaseView{
        void getResult(BrandBean brandBean);
        void getResult(Brand_Tv_infoBean result);
        void getResult(BrandIdBean result);
    }
    interface Presenter extends IBasePresenter<IBrand.View>{
        void getBrand(int p,int size);
        void getBrandtvinfo(int id);
        void getBrandId(int id);
    }
    interface Model extends IBaseModel{
        void getBrand(int p,int size,Callback callback);
        void getBrandtvinfo(int id,Callback callback);
        void getBrandId(int id,Callback callback);
    }
}

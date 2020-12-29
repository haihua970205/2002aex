package com.example.test_kao.interfaces;

import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.bean.HotGoodsBean;
import com.example.test_kao.bean.HotTopBean;

import java.util.HashMap;

public interface IHotGoods {
    interface View extends IBaseView{
        void getResult(HotTopBean result);
        void getResult(HotGoodsBean result);
    }
    interface Presenter extends IBasePresenter<IHotGoods.View>{
        void getHotTop();
        void getHotGoods(HashMap map);
    }
    interface Model extends IBaseModel{
        void getHotTop(Callback callback);
        void getHotGoods(HashMap map,Callback callback);
    }
}

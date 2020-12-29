package com.example.test_kao.presenter;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.HotGoodsBean;
import com.example.test_kao.bean.HotTopBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IHotGoods;
import com.example.test_kao.model.HotGoodsModel;
import com.example.test_kao.ui.home.hotgoods.HotGoodsActivity;

import java.util.HashMap;

public class HotGoodsPresenter extends BasePresenter<IHotGoods.View> implements IHotGoods.Presenter {
    IHotGoods.View view;
    IHotGoods.Model model;
    public HotGoodsPresenter(IHotGoods.View view) {
        this.view = view;
        model = new HotGoodsModel();
    }

    @Override
    public void getHotTop() {
        model.getHotTop(new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((HotTopBean) o);
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
    public void getHotGoods(HashMap map) {
        model.getHotGoods(map, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((HotGoodsBean) o);
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

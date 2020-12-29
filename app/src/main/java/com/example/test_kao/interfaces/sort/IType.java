package com.example.test_kao.interfaces.sort;

import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBaseModel;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.interfaces.IBaseView;
import com.example.test_kao.interfaces.IMain;

public interface IType {
    interface View extends IBaseView {
        void getResult(VTypeBean vTypeBean);
        void getResult(SortBean sortBean);
    }
    interface Presenter extends IBasePresenter<IType.View> {
        void getVType();
        void getSort(int id);
    }
    interface Model extends IBaseModel {
        void getVType(Callback callback);
        void getSort(int id,Callback callback);
    }
}

package com.example.test.interfaces;

import com.example.test.api.Callback;
import com.example.test.bean.InfoBean;

public interface IMain {
    interface View{
        void getResult(InfoBean resulr);
    }
    interface Presenter{
        void getData();
    }
    interface Model{
        void getData(Callback callback);
    }
}

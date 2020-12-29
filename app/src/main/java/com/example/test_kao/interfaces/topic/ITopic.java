package com.example.test_kao.interfaces.topic;

import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.bean.topic.TopicBean;
import com.example.test_kao.bean.topic.TopicRelated;
import com.example.test_kao.bean.topic.TopicdeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBaseModel;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.interfaces.IBaseView;
import com.example.test_kao.interfaces.IMain;

public interface ITopic {
    interface View extends IBaseView {
        void getResult(TopicBean topicBean);
        void getResult(TopicdeBean topicdeBean);
        void getResult(TopicRelated topicRelated);
    }
    interface Presenter extends IBasePresenter<ITopic.View> {
        void getTopic(int page);
        void getTopicde(int id);
        void getTopicRela(int id);
    }
    interface Model extends IBaseModel {
        void getTopic(int page,Callback callback);
        void getTopicde(int id,Callback callback);
        void getTopicRela(int id,Callback callback);
    }
}

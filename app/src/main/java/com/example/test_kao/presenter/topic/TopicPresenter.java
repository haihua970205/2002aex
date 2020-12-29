package com.example.test_kao.presenter.topic;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.topic.TopicBean;
import com.example.test_kao.bean.topic.TopicRelated;
import com.example.test_kao.bean.topic.TopicdeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.topic.ITopic;
import com.example.test_kao.model.topic.TopicModel;
import com.example.test_kao.ui.topic.TopicFrag;

public class TopicPresenter extends BasePresenter<ITopic.View> implements ITopic.Presenter {
    ITopic.View view;
    ITopic.Model model;
    public TopicPresenter(ITopic.View view) {
        this.view = view;
        model = new TopicModel();
    }

    @Override
    public void getTopic(int page) {
        model.getTopic(page,new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((TopicBean) o);
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
    public void getTopicde(int id) {
        model.getTopicde(id, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((TopicdeBean) o);
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
    public void getTopicRela(int id) {
        model.getTopicRela(id, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((TopicRelated) o);
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

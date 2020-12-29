package com.example.test_kao.model.topic;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.topic.TopicBean;
import com.example.test_kao.bean.topic.TopicRelated;
import com.example.test_kao.bean.topic.TopicdeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.topic.ITopic;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class TopicModel extends BaseModel implements ITopic.Model {
    @Override
    public void getTopic(int page,Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getTopic(page)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(callback) {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        callback.Scuess(topicBean);
                    }
                }));
    }

    @Override
    public void getTopicde(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getTopicde(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicdeBean>(callback) {
                    @Override
                    public void onNext(TopicdeBean topicdeBean) {
                        callback.Scuess(topicdeBean);
                    }
                }));
    }

    @Override
    public void getTopicRela(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getTopicRela(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicRelated>(callback) {
                    @Override
                    public void onNext(TopicRelated topicRelated) {
                        callback.Scuess(topicRelated);
                    }
                }));
    }
}

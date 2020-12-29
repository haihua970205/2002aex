package com.example.test_kao.ui.topic;

import android.content.Intent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kao.R;
import com.example.test_kao.adapter.topic.Topic_frag_Adapter;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.bean.topic.TopicBean;
import com.example.test_kao.bean.topic.TopicRelated;
import com.example.test_kao.bean.topic.TopicdeBean;
import com.example.test_kao.interfaces.topic.ITopic;
import com.example.test_kao.presenter.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TopicFrag extends BaseFragment<TopicPresenter> implements ITopic.View {

    @BindView(R.id.rlv_topic_frag)
    RecyclerView rlvTopicFrag;
    @BindView(R.id.first)
    TextView first;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.topic_nest)
    NestedScrollView topicNest;
    private ArrayList<TopicBean.DataBeanX.DataBean> tolists;
    private Topic_frag_Adapter topic_frag_adapter;

    @Override
    public int getLayout() {
        return R.layout.topic_frag;
    }

    @Override
    public void initView() {
        tolists = new ArrayList<>();
        rlvTopicFrag.setLayoutManager(new LinearLayoutManager(getActivity()));
        topic_frag_adapter = new Topic_frag_Adapter(getActivity(), tolists);
        rlvTopicFrag.setAdapter(topic_frag_adapter);

        //下一页
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tolists.clear();
                persenter.getTopic(2);
                //返回顶部
                topicNest.fullScroll(ScrollView.FOCUS_UP);
                topic_frag_adapter.notifyDataSetChanged();
            }
        });
        //上一页
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tolists.clear();
                persenter.getTopic(1);
                //返回顶部
                topicNest.fullScroll(ScrollView.FOCUS_UP);
                topic_frag_adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public TopicPresenter createPersenter() {
        return new TopicPresenter(this);
    }

    @Override
    public void initData() {
        persenter.getTopic(1);
    }

    @Override
    public void getResult(TopicBean topicBean) {
        List<TopicBean.DataBeanX.DataBean> data = topicBean.getData().getData();
        tolists.addAll(data);
        topic_frag_adapter.notifyDataSetChanged();

        topic_frag_adapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getActivity(), TopicActivity.class);
                intent.putExtra("id", data.get(pos).getId());
                startActivity(intent);
            }
        });


    }

    @Override
    public void getResult(TopicdeBean topicdeBean) {

    }

    @Override
    public void getResult(TopicRelated topicRelated) {

    }
}

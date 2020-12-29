package com.example.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.adapter.MAdapter;
import com.example.test.api.App;
import com.example.test.bean.InfoBean;
import com.example.test.interfaces.IMain;
import com.example.test.presenter.MPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rlv;
    private ArrayList<InfoBean.DataBean> lists;
    private MAdapter mAdapter;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRlv();
        //initData();
    }

    private void initView() {
        rlv = (RecyclerView) findViewById(R.id.rlv);
        send = (Button) findViewById(R.id.send);
        //send.setOnClickListener(this);
    }

    private void initRlv() {
        rlv.setLayoutManager(new LinearLayoutManager(this));
        lists = new ArrayList<>();
        mAdapter = new MAdapter(this, lists);
        rlv.setAdapter(mAdapter);

    }

//    private void initData() {
//        //MPresenter presenter = new MPresenter(this);
//        //presenter.getData();
//    }

//    @Override
//    public void getResult(InfoBean resulr) {
//        List<InfoBean.DataBean> data = resulr.getData();
//        lists.addAll(data);
//        //遍历集合给要更改的变量，设置初始值
//        for(int i=0;i<lists.size();i++){
//            lists.get(i).setSel(lists.get(i).isSelect());
//        }
//        mAdapter.notifyDataSetChanged();
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.send:
//                //点击提交判断两个boolean值是否相同
//                for(int i=0;i<lists.size();i++){
//                    if(lists.get(i).isSelect() != lists.get(i).isSel()){
//                        Log.e("111", "已更改" );
//                    }
//                }
//                break;
//        }
//    }
}

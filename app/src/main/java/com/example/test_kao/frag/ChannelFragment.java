package com.example.test_kao.frag;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.test_kao.R;
import com.example.test_kao.adapter.GoodsBeanAdapter;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.TypeBean;
import com.example.test_kao.interfaces.ICate;
import com.example.test_kao.presenter.InfoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelFragment extends BaseFragment<InfoPresenter> implements ICate.View {


    @BindView(R.id.tv_channel_name)
    TextView tvChannelName;
    @BindView(R.id.tv_channel_front_desc)
    TextView tvChannelFrontDesc;
    @BindView(R.id.rv_channel)
    RecyclerView rvChannel;
    private GoodsBeanAdapter goodsAdapter;
    private ArrayList<InfoBean.DataBeanX.GoodsListBean> goodsListBeans;
    private int id;

    @Override
    public int getLayout() {
        return R.layout.fragment_channel;
    }

    @Override
    public void initView() {

        id = getArguments().getInt( "id" );
        String name = getArguments().getString( "name" );
        String front_name = getArguments().getString( "front_name" );

        //设置值
        tvChannelName.setText(name);
        tvChannelFrontDesc.setText( front_name);

        //适配器
        rvChannel.setLayoutManager( new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL ) );

        goodsListBeans = new ArrayList<>();
        goodsAdapter = new GoodsBeanAdapter( getActivity(), goodsListBeans );
        rvChannel.setAdapter( goodsAdapter );

    }

    @Override
    public InfoPresenter createPersenter() {
        return new InfoPresenter(this);
    }

    @Override
    public void initData() {
        persenter.getCate( id );

    }
    @Override
    public void getResult(TypeBean typeBean) {

    }

    @Override
    public void getResult(InfoBean infoBean) {
        List<InfoBean.DataBeanX.GoodsListBean> goodsList = infoBean.getData().getGoodsList();
        goodsListBeans.addAll( goodsList );
        goodsAdapter.notifyDataSetChanged();
    }
}

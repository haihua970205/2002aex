package com.example.test_kao.ui.sort;

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

public class Sort_ch_frag extends BaseFragment<InfoPresenter> implements ICate.View {
    @BindView(R.id.rlv_sort_ch)
    RecyclerView rlvSortCh;
    private int id;

    @Override
    public int getLayout() {
        return R.layout.sort_ch_item;
    }

    @Override
    public void initView() {
        rlvSortCh.setLayoutManager(new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL ) );
    }

    @Override
    public InfoPresenter createPersenter() {
        return new InfoPresenter(this);
    }

    @Override
    public void initData() {
        persenter.getCate(id);
    }

    public void setData(int id) {
        this.id = id;
    }


    @Override
    public void getResult(TypeBean typeBean) {

    }

    @Override
    public void getResult(InfoBean infoBean) {
        List<InfoBean.DataBeanX.GoodsListBean> data = infoBean.getData().getGoodsList();
        GoodsBeanAdapter goodsAdapter = new GoodsBeanAdapter( getActivity(),data);
        rlvSortCh.setAdapter( goodsAdapter );
        goodsAdapter.notifyDataSetChanged();
    }
}

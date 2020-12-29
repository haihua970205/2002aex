package com.example.test_kao.frag;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.adapter.sort.SortAdapter;
import com.example.test_kao.app.MyApp;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.interfaces.sort.IType;
import com.example.test_kao.presenter.sort.TypePresenter;
import com.example.test_kao.ui.home.channel.ChannelActivity;
import com.example.test_kao.ui.sort.SortActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryTabFragment extends BaseFragment<TypePresenter> implements IType.View {

    @BindView(R.id.img_sort)
    ImageView imgSort;
    @BindView(R.id.front_name)
    TextView frontName;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.rlv_sort)
    RecyclerView rlvSort;
    private int id;
    @Override
    public int getLayout() {
        return R.layout.item_rlv_sort;
    }

    public void setData(int id) {
        this.id = id;
    }

    @Override
    public void initView() {
    }


    @Override
    public TypePresenter createPersenter() {
        return new TypePresenter(this);
    }

    @Override
    public void initData() {
        persenter.getSort(id);
    }

    @Override
    public void getResult(SortBean sortBean) {
        if (sortBean!=null){
            if (!isAdded()){return;}
            SortBean.DataBean.CurrentCategoryBean cub = sortBean.getData().getCurrentCategory();

            List<SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list =
                    cub.getSubCategoryList();
            Glide.with(getActivity()).load(cub.getWap_banner_url()).into(imgSort);
            tvSort.setText(cub.getName());
            frontName.setText(cub.getFront_name());

            rlvSort.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            SortAdapter sortAdapter = new SortAdapter(getActivity(), list);
            rlvSort.setAdapter(sortAdapter);


        sortAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getActivity(),SortActivity.class);
                intent.putExtra("name",list.get(pos).getName());
                MyApp.getMap().put("li",list);
                startActivity(intent);
            }
        });
        }
    }

    @Override
    public void getResult(VTypeBean vTypeBean) {
    }


}

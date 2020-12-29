package com.example.test_kao.ui.sort;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.adapter.sort.SortAdapter;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.interfaces.sort.IType;
import com.example.test_kao.presenter.sort.TypePresenter;

import java.util.ArrayList;
import java.util.List;


import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class SortFrag extends BaseFragment<TypePresenter> implements IType.View, VerticalTabLayout.OnTabSelectedListener {

    private List<String> titles;
    private LinearLayout llSort;
    private VerticalTabLayout tab;
    private int posi;
    private RecyclerView rlv_sort;

    @Override
    public int getLayout() {
        return R.layout.sort_fragment;
    }

    @Override
    public void initView() {
        titles = new ArrayList<>();
        llSort = getActivity().findViewById(R.id.ll_sort);
        tab = getActivity().findViewById(R.id.tab);
    }

    @Override
    public TypePresenter createPersenter() {
        return new TypePresenter(this);
    }

    @Override
    public void initData() {
        persenter.getVType();

    }

    @Override
    public void getResult(VTypeBean vTypeBean) {
        List<VTypeBean.DataBean.CategoryListBean> categoryList = vTypeBean.getData().getCategoryList();

        for (int i = 0; i < categoryList.size(); i++) {
            int id = categoryList.get(i).getId();
            titles.add(categoryList.get(i).getName());
            persenter.getSort(id);
        }

        tab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                ITabView.TabTitle title = new ITabView.TabTitle.Builder()
                        .setContent(titles.get(position))//设置数据   也有设置字体颜色的方法
                        .build();
                return title;
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });


    }

    @Override
    public void getResult(SortBean sortBean) {
        SortBean.DataBean.CurrentCategoryBean cub = sortBean.getData().getCurrentCategory();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_rlv_sort, null);

        ImageView imgSort = inflate.findViewById(R.id.img_sort);
        TextView tvSort = inflate.findViewById(R.id.tv_sort);
        TextView frontName = inflate.findViewById(R.id.front_name);
        Glide.with(getActivity()).load(cub.getBanner_url()).into(imgSort);
        tvSort.setText(cub.getFront_desc());
        frontName.setText(cub.getFront_name());

        List<SortBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list =
                cub.getSubCategoryList();
        rlv_sort = inflate.findViewById(R.id.rlv_sort);
        rlv_sort.setLayoutManager(new GridLayoutManager(getActivity(),3));
        SortAdapter sortAdapter = new SortAdapter(getActivity(), list);
        rlv_sort.setAdapter(sortAdapter);
        llSort.addView(inflate);

        sortAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                posi = pos;
            }
        });

    }

    @Override
    public void onTabSelected(TabView tab, int position) {

    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }
}

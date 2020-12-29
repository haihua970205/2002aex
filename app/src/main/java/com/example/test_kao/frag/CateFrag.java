package com.example.test_kao.frag;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.test_kao.R;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.interfaces.sort.IType;
import com.example.test_kao.presenter.sort.TypePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class CateFrag extends BaseFragment<TypePresenter> implements IType.View {
    @BindView(R.id.vTab)
    VerticalTabLayout vTab;
    @BindView(R.id.vp_sort)
    ViewPager vpSort;
    private ArrayList<CategoryTabFragment> fragments;
    private ArrayList<String> tabName;

    @Override
    public int getLayout() {
        return R.layout.catefrag;
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
        persenter.getVType();
    }

    @Override
    public void getResult(VTypeBean vTypeBean) {
        List<VTypeBean.DataBean.CategoryListBean> categoryList = vTypeBean.getData().getCategoryList();
        tabName = new ArrayList<>();
        fragments = new ArrayList<>();
        if (!isAdded()){return;}
        for (int i = 0; i < categoryList.size(); i++) {
            CategoryTabFragment categoryTabFragment = new CategoryTabFragment();
            categoryTabFragment.setData(categoryList.get(i).getId());
            fragments.add(categoryTabFragment);
            tabName.add(categoryList.get(i).getName());
        }
        FragTabAdapter fragTabAdapter = new FragTabAdapter(getParentFragmentManager());
        vpSort.setAdapter(fragTabAdapter);
        vTab.setupWithViewPager(vpSort);

    }

    @Override
    public void getResult(SortBean sortBean) {

    }

    class FragTabAdapter extends FragmentPagerAdapter {

        public FragTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabName.get(position);
        }

    }
}

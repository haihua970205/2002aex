package com.example.test_kao.ui.me;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kao.R;
import com.example.test_kao.adapter.me.FavoritesAdapter;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.interfaces.IBasePresenter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
//TODO   数据库
public class FavoritesActivity extends BaseActivity {
    @BindView(R.id.rlv_favorites)
    SwipeMenuRecyclerView rlvFavorites;
    private List<ShoppingBean> list;
    private FavoritesAdapter favoritesAdapter;
    private int adapterPosition;

    @Override
    protected int getLayout() {
        return R.layout.activity_favorites;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        Realm realm = Realms.getRealm(this);
        //查询方法
        RealmResults<ShoppingBean> result = realm.where(ShoppingBean.class).findAll();
//        realm.executeTransaction(new io.realm.Realm.Transaction() {
//            @Override
//            public void execute(io.realm.Realm realm) {
//                result.get(result.size() - 1).deleteFromRealm();
//            }
//        });

        list = new ArrayList<>();
        list.addAll(result);
        rlvFavorites.setLayoutManager(new LinearLayoutManager(this));
        rlvFavorites.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));


        favoritesAdapter = new FavoritesAdapter(this, list);
        // 设置菜单创建器
        rlvFavorites.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听
        rlvFavorites.setSwipeMenuItemClickListener(itemClickListener);
        rlvFavorites.setAdapter(favoritesAdapter);

//        realm.beginTransaction();
//        list.get(adapterPosition).deleteFromRealm();
//        realm.commitTransaction();

    }

    //创建侧滑菜单
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            SwipeMenuItem deleteItem = new SwipeMenuItem(FavoritesActivity.this)
                    .setImage(R.drawable.laji)
                    .setWidth(144)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //高（MATCH_PARENT意为Item多高侧滑菜单多高 （推荐使用））
            swipeRightMenu.addMenuItem(deleteItem);//添加一个条目在右侧菜单
        }
    };

    //侧滑菜单的点击事件
    private SwipeMenuItemClickListener itemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            //在menuBridge中我们可以得到侧滑的这一项item的position (menuBridge.getAdapterPosition())
            adapterPosition = menuBridge.getAdapterPosition();
            list.remove(adapterPosition);
            favoritesAdapter.notifyDataSetChanged();
        }
    };
    @Override
    protected void initData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

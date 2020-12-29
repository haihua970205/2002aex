package com.example.test_kao.ui.me;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test_kao.R;
import com.example.test_kao.adapter.me.MeAdapter;
import com.example.test_kao.base.BaseFragment;
import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.ui.login.LoginActivity;
import com.example.test_kao.utils.ImageLoader;
import com.example.test_kao.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MeFrag extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.login_head)
    ImageView loginHead;
    @BindView(R.id.login_name)
    TextView loginName;
    @BindView(R.id.jump_login)
    ImageView jumpLogin;
    @BindView(R.id.line1)
    RelativeLayout line1;
    @BindView(R.id.channel)
    GridView channel;
    @BindView(R.id.login_nickname)
    TextView loginNickname;
    private String username;

    public static final int LOGIN_ME = 10001; //登录成功的回传值
    public static final int LOGINOUT_ME = 10002; //退出登录的回传

    @Override
    public int getLayout() {
        return R.layout.me_frag;
    }

    @Override
    public void initView() {
        loginName.setOnClickListener(this);
        loginHead.setOnClickListener(this);
        jumpLogin.setOnClickListener(this);


    }

    @Override
    public BasePresenter createPersenter() {
        return null;
    }

    @Override
    public void initData() {
        String token = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(token)) {
            isLogin(true);
        } else {
            isLogin(false);
        }
        inintNineData();
    }

    //登录状态的界面控制
    private void isLogin(boolean b) {
        if (b) {
            loginNickname.setVisibility(View.VISIBLE);
            loginName.setVisibility(View.GONE);
            String username = SpUtils.getInstance().getString("username");
            Log.e("111", "isLogin: "+username);
            String nickname = SpUtils.getInstance().getString("nickname");
            String avatar = SpUtils.getInstance().getString("avatar");
            String mark = SpUtils.getInstance().getString("mark");
            if(!TextUtils.isEmpty(nickname)){
                loginNickname.setText(nickname);
            }else{
                loginNickname.setText(username);
            }
//            ImageLoader.loadImage(avatar,loginHead);

            String img = SpUtils.getInstance().getString("img");
            if(!TextUtils.isEmpty(img)){
                Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(loginHead);
            }
        }else{
            loginNickname.setVisibility(View.GONE);
            loginName.setVisibility(View.VISIBLE);
        }
    }

    private void inintNineData() {
        int[] img = new int[]{
                R.mipmap.dingdan,
                R.mipmap.youhui,
                R.mipmap.lipin,
                R.mipmap.ic_menu_shopping_nor,
                R.mipmap.anquan,
                R.mipmap.kefu,
                R.mipmap.dingwei,
                R.mipmap.anquan,
                R.mipmap.denglu,
                R.mipmap.bangzhu,
                R.mipmap.yijian
        };
        String[] title = new String[]{
                "我的订单",
                "优惠券",
                "礼品卡",
                "我的收藏",
                "我的足迹",
                "会员福利",
                "地址管理",
                "账号安全",
                "联系客服",
                "帮助中心",
                "意见反馈"
        };
        List<MyBean> list = new ArrayList<MyBean>();
        for (int i = 0; i < title.length; i++) {
            MyBean myBean = new MyBean(title[i], img[i]);
            list.add(myBean);
        }
        MeAdapter meAdapter = new MeAdapter(getActivity(), list);
        channel.setAdapter(meAdapter);

        meAdapter.setMyList(new MeAdapter.MyList() {
            @Override
            public void setOnClick(int position) {
                //收藏页面
                if (position == 3) {
                    Intent intent = new Intent(getActivity(), FavoritesActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_name:
                String token = SpUtils.getInstance().getString("token");
                if (TextUtils.isEmpty(token)) {
                    openLogin();//等于空跳转到登录
                }
                break;
            case R.id.login_head:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.jump_login:
                startActivity(new Intent(getActivity(), UserInfoDetailActivity.class));
                break;
        }
    }


    private void openLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, LOGIN_ME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_ME && resultCode == 300) {
            username = data.getStringExtra("username");
            loginName.setText(username);
        }
    }
}

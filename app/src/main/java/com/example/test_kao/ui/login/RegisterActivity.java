package com.example.test_kao.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test_kao.R;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.bean.login.LoginBean;
import com.example.test_kao.bean.login.RegisterBean;
import com.example.test_kao.interfaces.login.ILogin;
import com.example.test_kao.presenter.login.LoginPresenter;
import com.example.test_kao.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<LoginPresenter> implements ILogin.View {
    @BindView(R.id.me_regist_input_username)
    EditText meRegistInputUsername;
    @BindView(R.id.me_regist_input_pw)
    EditText meRegistInputPw;
    @BindView(R.id.me_regist_input_pw2)
    EditText meRegistInputPw2;
    @BindView(R.id.me_regist_input_verification)
    EditText meRegistInputVerification;
    @BindView(R.id.me_regist_btn_verification)
    ImageView meRegistBtnVerification;
    @BindView(R.id.me_regist_btn_regist)
    Button meRegistBtnRegist;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected LoginPresenter createPrenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

    }

    @Override
    public void loginReturn(LoginBean loginBean) {

    }

    @Override
    public void registerReturn(RegisterBean registerBean) {

        String name = meRegistInputUsername.getText().toString();
        String pwd = meRegistInputPw.getText().toString();
        Intent intent = getIntent();
        intent.putExtra("name",name);
        intent.putExtra("pwd",pwd);
        setResult(200,intent);
        finishAndRemoveTask();//关闭当前页面返回之前页面
    }

    @OnClick({R.id.me_regist_btn_verification, R.id.me_regist_btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.me_regist_btn_verification: //验证码
                break;
            case R.id.me_regist_btn_regist: //注册
                initRegister();
                break;
        }
    }

    private void initRegister() {
        String name = meRegistInputUsername.getText().toString();
        String pwd = meRegistInputPw.getText().toString();
        String pwd2 = meRegistInputPw2.getText().toString();
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pwd)&&!TextUtils.isEmpty(pwd2)){
            if(pwd.length()>=6){
                presenter.register(name,pwd);
            }else{
                Toast.makeText(this, "密码长度为6位", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "不为空", Toast.LENGTH_SHORT).show();
        }

    }
}

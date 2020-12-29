package com.example.test_kao.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

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


/**
 * 密码必须是8位以上  字母+数字组合
 */

public class LoginActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {

    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.btn_regi)
    Button btnRegi;
    private String username;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected ILogin.Presenter createPrenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        imgPw.setTag(2);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_login, R.id.img_pw,R.id.btn_regi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_regi:
                regi();
                break;
            case R.id.img_pw:
                int tag = (int) imgPw.getTag();
                if (tag == 1) {
                    //为1能看到
                    imgPw.setImageResource(R.mipmap.ic_pw_open);
                    imgPw.setTag(2);

                    inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgPw.setImageResource(R.mipmap.ic_pw_close);
                    imgPw.setTag(1);
                    inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;

        }
    }

    private void regi() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(intent, 300);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 300 && resultCode == 200){
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");
            inputUsername.setText(name);
            inputPw.setText(pwd);
        }
    }

    private void login() {
        username = inputUsername.getText().toString();
        String pw = inputPw.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)) {
            presenter.login(username, pw);
        } else {
            Toast.makeText(this, "需要登录", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void loginReturn(LoginBean loginBean) {
        if (!TextUtils.isEmpty(loginBean.getData().getToken())) {
            SpUtils.getInstance().setValue("token", loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid", loginBean.getData().getUserInfo().getUid());
            SpUtils.getInstance().setValue("username",loginBean.getData().getUserInfo().getUsername());
            SpUtils.getInstance().setValue("avatar",loginBean.getData().getUserInfo().getAvatar());
            SpUtils.getInstance().setValue("nickname",loginBean.getData().getUserInfo().getNickname());

            Intent intent = new Intent();
            intent.putExtra("username",username);
            setResult(300,intent);
            finish();
        }
    }

    @Override
    public void registerReturn(RegisterBean registerBean) {

    }
}

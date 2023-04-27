package com.ldd.transport.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.hzlh.sdk.net.CallBack;
import com.hzlh.sdk.util.SPUtils;
import com.hzlh.sdk.util.StringUtils;
import com.ldd.transport.R;
import com.ldd.transport.api.user.LoginBean;
import com.ldd.transport.api.user.UserApi;
import com.ldd.transport.api.user.UserInfoBean;
import com.ldd.transport.common.Constants;
import com.ldd.transport.ui.mine.LoginActivity;

/**
 * 描述：启动页
 * 日期: 2022/5/17 9:17
 *
 * @author Zhout
 */
public class StartActivity extends AppCompatActivity {
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImmersionBar.with(this).fullScreen(true).init();

        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLogin();
            }
        }, 1000);
    }


    /**
     * 检查是否要自动登录
     */
    private void checkLogin() {
        String userNameStr = SPUtils.getInstance(context).getString(Constants.SP_KEY_LOGIN_ACCOUNT);
        String passwordStr = SPUtils.getInstance(context).getString(Constants.SP_KEY_LOGIN_PWD);
        if (StringUtils.isEmpty(passwordStr)) {//说明是没登录过app
            gotoLogin();
            return;
        }

        new UserApi().login(context, userNameStr, passwordStr,
                new CallBack<LoginBean>(context) {
                    @Override
                    public void onResultOk(LoginBean result) {
                        super.onResultOk(result);
                        if (result.getCode() == 200) {
                            if (result.getData() != null) {
                                Constants.accessToken = result.getData().getToken();
                                Constants.authCode = result.getData().getAuth_code();
                                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_ACCOUNT, userNameStr);
                                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_PWD, passwordStr);
                                getUserInfo();
                            }
                        } else {
                            gotoLogin();
                        }

                    }

                    @Override
                    public void onNull() {
                        super.onNull();
                        gotoLogin();
                    }
                });
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        new UserApi().getInfo(this, new CallBack<UserInfoBean>(this) {
            @Override
            public void onResultOk(UserInfoBean result) {
                super.onResultOk(result);
                if (result.getCode() == 200) {
                    Constants.userInfoBean = result;
                    Intent main = new Intent(context, MainActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    gotoLogin();
                }
            }

            @Override
            public void onNull() {
                super.onNull();
                gotoLogin();
            }
        });
    }


    private void gotoLogin() {
        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }

}
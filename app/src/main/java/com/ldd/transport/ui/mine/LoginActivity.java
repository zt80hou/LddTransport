package com.ldd.transport.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.hzlh.sdk.net.CallBack;
import com.hzlh.sdk.util.Base64Encoder;
import com.hzlh.sdk.util.SPUtils;
import com.hzlh.sdk.util.StringUtils;
import com.hzlh.sdk.util.YTextWatcher;
import com.hzlh.sdk.util.YToast;
import com.ldd.transport.ui.MainActivity;
import com.ldd.transport.R;
import com.ldd.transport.api.user.LoginBean;
import com.ldd.transport.api.user.UserApi;
import com.ldd.transport.api.user.UserInfoBean;
import com.ldd.transport.common.AppBaseActivity;
import com.ldd.transport.common.AppType;
import com.ldd.transport.common.Constants;
import com.ldd.transport.databinding.ActivityLoginBinding;
import com.ldd.transport.util.DialogUtils;

/**
 * 描述：登录页
 * 日期: 2022/9/15 16:43
 *
 * @author Zhout
 */
public class LoginActivity extends AppBaseActivity<ActivityLoginBinding> implements View.OnClickListener {
    private final Context context = this;
    private boolean isPwdFocus = false;

    @Override
    protected void initAppView() {
        super.initAppView();
        initHeader("");
        backImg.setVisibility(View.GONE);
        vb.ivClearAccount.setOnClickListener(this);
        vb.ivClearPwd.setOnClickListener(this);
        vb.loginBtn.setOnClickListener(this);
        vb.agreeTxt.setOnClickListener(this);
        vb.ivEye.setOnClickListener(this);
        vb.ivEye.setTag(AppType.STR_0);

        // 账号记忆
        String account = SPUtils.getInstance(this).getString(Constants.SP_KEY_LOGIN_ACCOUNT);
        if (StringUtils.isNotEmpty(account)) {
            vb.etAccount.setText(account);
            vb.ivClearAccount.setVisibility(View.VISIBLE);
            vb.etAccount.setSelection(account.length());
        }

        vb.etAccount.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(vb.etAccount.getText().toString())) {
                    vb.ivClearAccount.setVisibility(View.GONE);
                } else {
                    vb.ivClearAccount.setVisibility(View.VISIBLE);
                }
            }

        });

        vb.etAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus || TextUtils.isEmpty(vb.etAccount.getText().toString())) {
                    vb.ivClearAccount.setVisibility(View.GONE);
                } else {
                    vb.ivClearAccount.setVisibility(View.VISIBLE);
                }

            }
        });
        vb.etPwd.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(vb.etPwd.getText().toString())) {
                    vb.ivClearPwd.setVisibility(View.GONE);
                } else {
                    if (isPwdFocus) {
                        vb.ivClearPwd.setVisibility(View.VISIBLE);
                    } else {
                        vb.ivClearPwd.setVisibility(View.GONE);
                    }
                }
            }
        });
        vb.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isPwdFocus = hasFocus;
                if (!hasFocus || TextUtils.isEmpty(vb.etPwd.getText().toString())) {
                    vb.ivClearPwd.setVisibility(View.GONE);
                } else {
                    vb.ivClearPwd.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void setPassOpen() {
        if (AppType.STR_0.equals(vb.ivEye.getTag())) {
            vb.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            vb.ivEye.setImageResource(R.drawable.ic_eye_opening);
            vb.ivEye.setTag(AppType.STR_1);
        } else {
            vb.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            vb.ivEye.setImageResource(R.drawable.ic_eye_closed);
            vb.ivEye.setTag(AppType.STR_0);
        }
        vb.etPwd.setSelection(vb.etPwd.getText().length());
    }

    @Override
    public void onClick(View view) {
        if (view == vb.ivClearAccount) {
            vb.etAccount.setText("");
        } else if (view == vb.ivClearPwd) {
            vb.etPwd.setText("");
        } else if (view == vb.ivEye) {
            setPassOpen();
        } else if (view == vb.loginBtn) {
            checkLogin();
        }
    }


    private void checkLogin() {
        String userNameStr = vb.etAccount.getText().toString().trim();
        String passwordStr = vb.etPwd.getText().toString().trim();

        if (StringUtils.isEmpty(userNameStr)) {
            YToast.shortToast(context, "请输入汇目云账号");
            return;
        }
        if (StringUtils.isEmpty(passwordStr)) {
            YToast.shortToast(context, "请输入密码");
            return;
        }

        DialogUtils.showWaitDialog(context, "正在登录...");

        String pwdBase64 = Base64Encoder.encode(passwordStr);
        new UserApi().login(context, userNameStr, pwdBase64,
                new CallBack<LoginBean>(context) {
                    @Override
                    public void onResultOk(LoginBean result) {
                        super.onResultOk(result);
                        DialogUtils.dismissDialog();
                        if (result.getCode() == 200) {
                            if (result.getData() != null) {
                                Constants.accessToken = result.getData().getToken();
                                Constants.authCode = result.getData().getAuth_code();
                                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_ACCOUNT, userNameStr);
                                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_PWD, pwdBase64);
                                getUserInfo();
                            }
                        } else {
                            YToast.shortToast(context, result.getMsg());
                        }

                    }

                    @Override
                    public void onNull() {
                        super.onNull();
                        DialogUtils.dismissDialog();
                    }
                });
    }

    private void getUserInfo() {
        new UserApi().getInfo(this, new CallBack<UserInfoBean>(this) {
            @Override
            public void onResultOk(UserInfoBean result) {
                super.onResultOk(result);
                if (result.getCode() == 200) {
                    Constants.userInfoBean = result;
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                }

            }
        });
    }


}

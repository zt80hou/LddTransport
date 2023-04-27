package com.ldd.transport.api.user;

import android.content.Context;

import com.hzlh.sdk.net.CallBack;
import com.hzlh.sdk.test.ZRequest;
import com.ldd.transport.common.Constants;
import com.ldd.transport.util.JsonUtil;

import java.util.HashMap;

/**
 * 描述：用户相关的api
 * 日期: 2022/9/19 16:45
 *
 * @author Zhout
 */
public class UserApi {


    /**
     * 登录
     *
     * @param context  上下文
     * @param username 账号
     * @param password 密码
     * @param callBack 回调
     */
    public void login(Context context, String username, String password, CallBack<LoginBean> callBack) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        String url = Constants.DOMAIN + "/omhmy/login";
        ZRequest.getInstance().postJson(context, url, JsonUtil.toJson(map), LoginBean.class, callBack);
    }


    /**
     * 退出登录
     *
     * @param context  上下文
     * @param callBack 回调
     */
    public void logout(Context context, CallBack<CommonResultBean> callBack) {
        String url = Constants.DOMAIN + "/omhmy/loginOut";
        ZRequest.getInstance().addHeader("AccessToken", Constants.accessToken)
                .addHeader("auth_code", Constants.authCode).get(context, url, CommonResultBean.class, new HashMap<>(),callBack);
    }

    /**
     * 获取用户信息
     *
     * @param context
     * @param callBack
     */
    public void getInfo(Context context, CallBack<UserInfoBean> callBack) {
        HashMap<String, String> map = new HashMap<>();
        String url = Constants.DOMAIN + "/omhmy/getInfo";
        ZRequest.getInstance().addHeader("AccessToken", Constants.accessToken)
                .addHeader("auth_code", Constants.authCode)
                .get(context, url, UserInfoBean.class, map, callBack);
    }


    /**
     * 修改密码
     *
     * @param context     上下文
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param callBack    回调
     * @author yukd
     */
    public void resetPwd(Context context, String oldPassword, String newPassword, CallBack<CommonResultBean> callBack) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("oldPassword", oldPassword);
        map.put("newPassword", newPassword);
        String url = Constants.DOMAIN + "/omhmy/user/resetPwd";
        ZRequest.getInstance().addHeader("AccessToken", Constants.accessToken)
                .addHeader("auth_code", Constants.authCode).postJson(context, url, JsonUtil.toJson(map), CommonResultBean.class, callBack);
    }

    /**
     * 修改用户信息(头像 昵称)
     *
     * @param context  上下文
     * @param nickName 昵称
     * @param avatar   头像
     * @param callBack 回调
     * @author yukd
     */
    public void editBase(Context context, String nickName, String avatar, CallBack<CommonResultBean> callBack) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("nickName", nickName);
        map.put("avatar", avatar);
        map.put("userId", Constants.userInfoBean.getData().getUserId());
        String url = Constants.DOMAIN + "/omhmy/user/editBase";
        ZRequest.getInstance().addHeader("AccessToken", Constants.accessToken)
                .addHeader("auth_code", Constants.authCode).postJson(context, url, JsonUtil.toJson(map), CommonResultBean.class, callBack);
    }


}



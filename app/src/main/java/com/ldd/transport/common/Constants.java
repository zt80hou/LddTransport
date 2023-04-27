package com.ldd.transport.common;

import com.ldd.transport.api.user.UserInfoBean;

import java.util.ArrayList;
import java.util.List;


public class Constants {
    /**
     * 系统常量
     */
    public static final int ENVIRONMENT_TYPE = 1;//1测试环境；2线上
    public static String accessToken = "";//token
    public static String authCode = "";//auth_code
    public static UserInfoBean userInfoBean;


    public static List<Integer> labelAppIndexList = new ArrayList<>();// 已选择的筛选应用的名称index列表
    public static List<Integer> labelDeviceIndexList = new ArrayList<>();// 已选择的筛选设备的名称index列表
    public static String subList = "";// 已选择的筛选应用的名称 id集合， ,分开
    public static String videoList = "";// 已选择的筛选设备名称 设备id集合 ,分开

    public static String DEVICE_NAME = "gxzqadmin";// 设备名，别名

    /**
     * API请求相关常量
     */
    public static final String DOMAIN = "https://eye.linker.cc"; //接口地址
//      public static final String DOMAIN = "http://192.168.0.56";//测试
//      public static final String DOMAIN = "http://10.8.15.4";//测试


    /**
     * sp相关
     */
    public static final String SP_KEY_LOGIN_ACCOUNT = "login_account";// 登录账户
    public static final String SP_KEY_LOGIN_PWD = "login_password";// 密码

}

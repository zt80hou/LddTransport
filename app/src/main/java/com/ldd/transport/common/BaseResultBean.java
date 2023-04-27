package com.ldd.transport.common;

/**
 * 描述：请求结果基类 BaseResultBean
 * 日期: 2022/4/6 11:12
 *
 * @author Zhout
 */
public class BaseResultBean<T> {
    private int code;//错误码 200成功； 400 失败
    private String msg;//信息描述
    protected T data; //数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

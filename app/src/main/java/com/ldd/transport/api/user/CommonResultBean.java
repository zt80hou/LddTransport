
package com.ldd.transport.api.user;


/**
 * 描述：通用接口回调结果 code msg data
 * 日期: 2022年9月20日
 *
 * @author yukd
 */
public class CommonResultBean {

    private Long code;
    private String data;
    private String msg;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return code+" "+ msg+" "+data;
    }
}

package com.ldd.transport.api.user;

import com.ldd.transport.common.BaseResultBean;


/**
 * 描述：登录返回的模型
 * 日期: 2022/9/19 16:50
 *
 * @author Zhout
 */
public class LoginBean extends BaseResultBean<LoginBean.DataBean> {

    public static class DataBean {
        private String is_admin;
        private int check_type;
        private String logoUrl;
        private String token;
        private String auth_code;

        public String getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(String is_admin) {
            this.is_admin = is_admin;
        }

        public int getCheck_type() {
            return check_type;
        }

        public void setCheck_type(int check_type) {
            this.check_type = check_type;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAuth_code() {
            return auth_code;
        }

        public void setAuth_code(String auth_code) {
            this.auth_code = auth_code;
        }
    }
}

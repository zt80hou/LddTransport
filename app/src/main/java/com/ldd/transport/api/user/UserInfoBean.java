package com.ldd.transport.api.user;

import com.ldd.transport.common.BaseResultBean;


/**
 * 描述：用户信息
 * 日期: 2022/9/19 17:02
 *
 * @author Zhout
 */
public class UserInfoBean extends BaseResultBean<UserInfoBean.DataBean> {

    public static class DataBean {// 接口返回了很多字段，只取用了一部分
        private String userId;//用户id
        private String orgId;// 机构id
        private String userName;//用户名
        private String nickName;//昵称
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String logoUrl;
        private String expiryDate;//过期时间

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }
    }
}

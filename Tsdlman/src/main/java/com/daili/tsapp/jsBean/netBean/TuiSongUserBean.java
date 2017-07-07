package com.daili.tsapp.jsBean.netBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class TuiSongUserBean {

    private String flag;
    private int num;
    private List<MsgBean> msg;
    private List<?> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public static class MsgBean {
        private String id;
        private String user_name;
        private String user_telphone;
        private String user_password;
        private String user_head_pic;
        private String tjr_tel_phone;
        private String register_date;
        private String user_sex;
        private String belong_industry;
        private String user_qq;
        private String user_email;
        private String second_user_name;
        private String second_user_tel;
        private String user_address;
        private  int new_gays;
        public int getNew_gays() {
            return new_gays;
        }

        public void setNew_gays(int new_gays) {
            this.new_gays = new_gays;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_telphone() {
            return user_telphone;
        }

        public void setUser_telphone(String user_telphone) {
            this.user_telphone = user_telphone;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public Object getUser_head_pic() {
            return user_head_pic;
        }

        public void setUser_head_pic(String user_head_pic) {
            this.user_head_pic = user_head_pic;
        }

        public Object getTjr_tel_phone() {
            return tjr_tel_phone;
        }

        public void setTjr_tel_phone(String tjr_tel_phone) {
            this.tjr_tel_phone = tjr_tel_phone;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public Object getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public Object getBelong_industry() {
            return belong_industry;
        }

        public void setBelong_industry(String belong_industry) {
            this.belong_industry = belong_industry;
        }

        public Object getUser_qq() {
            return user_qq;
        }

        public void setUser_qq(String user_qq) {
            this.user_qq = user_qq;
        }

        public Object getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public Object getSecond_user_name() {
            return second_user_name;
        }

        public void setSecond_user_name(String second_user_name) {
            this.second_user_name = second_user_name;
        }

        public Object getSecond_user_tel() {
            return second_user_tel;
        }

        public void setSecond_user_tel(String second_user_tel) {
            this.second_user_tel = second_user_tel;
        }

        public Object getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }
    }
}

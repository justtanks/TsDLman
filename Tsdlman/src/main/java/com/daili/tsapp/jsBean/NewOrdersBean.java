package com.daili.tsapp.jsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 * 通过用户端提交电话和商标名称进行注册得到的订单信息
 */

public class NewOrdersBean {

    /**
     * flag : Success
     * msg : [{"id":"17","trademark_name":"123","new_list":"0","user_tel":"123","put_down_time":"2017-07-21 09:13:04"}]
     * data : []
     * num : 1
     */

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
        /**
         * id : 17
         * trademark_name : 123
         * new_list : 0
         * user_tel : 123
         * put_down_time : 2017-07-21 09:13:04
         */

        private String id;
        private String trademark_name;
        private int new_list;
        private String user_tel;
        private String put_down_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTrademark_name() {
            return trademark_name;
        }

        public void setTrademark_name(String trademark_name) {
            this.trademark_name = trademark_name;
        }

        public int getNew_list() {
            return new_list;
        }

        public void setNew_list(int new_list) {
            this.new_list = new_list;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getPut_down_time() {
            return put_down_time;
        }

        public void setPut_down_time(String put_down_time) {
            this.put_down_time = put_down_time;
        }
    }
}

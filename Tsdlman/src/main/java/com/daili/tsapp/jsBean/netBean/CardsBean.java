package com.daili.tsapp.jsBean.netBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class CardsBean implements Serializable{
    /**
     *显示代理人所有银行卡
     * num : 1
     */

    private String flag;
    private String msg;
    private int num;
    private List<DataBean> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * waiter_id : 71
         * brank_name : 1哈哈哈
         * card_num : 2147483647
         * add_time : 2017-03-25 09:25:
         *
         */

        private String id;
        private String waiter_id;
        private String brank_name;
        private String card_num;
        private String add_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWaiter_id() {
            return waiter_id;
        }

        public void setWaiter_id(String waiter_id) {
            this.waiter_id = waiter_id;
        }

        public String getBrank_name() {
            return brank_name;
        }

        public void setBrank_name(String brank_name) {
            this.brank_name = brank_name;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}

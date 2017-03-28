package com.daili.tsapp.jsBean.netBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 * 首页数据报表数据
 */

public class HomeBaobiaoBean {

    /**
     * flag : Success
     * msg : [{"today_money":"2200","today_order":"2","this_month_money":"2200","last_month_money":"0","all_price":"2200","yesterday_counts":"0","yesterday_money":"0","before_yesterday_counts":"0","before_yesterday_money":"0","type":1}]
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
         * today_money : 2200
         * today_order : 2
         * this_month_money : 2200
         * last_month_money : 0
         * all_price : 2200
         * yesterday_counts : 0
         * yesterday_money : 0
         * before_yesterday_counts : 0
         * before_yesterday_money : 0
         * type : 1
         */

        private String today_money;
        private String today_order;
        private String this_month_money;
        private String last_month_money;
        private String all_price;
        private String yesterday_counts;
        private String yesterday_money;
        private String before_yesterday_counts;
        private String before_yesterday_money;
        private  int balance_money;
        private int type;

        public int getBalance_money() {
            return balance_money;
        }

        public void setBalance_money(int balance_money) {
            this.balance_money = balance_money;
        }

        public String getToday_money() {
            return today_money;
        }

        public void setToday_money(String today_money) {
            this.today_money = today_money;
        }

        public String getToday_order() {
            return today_order;
        }

        public void setToday_order(String today_order) {
            this.today_order = today_order;
        }

        public String getThis_month_money() {
            return this_month_money;
        }

        public void setThis_month_money(String this_month_money) {
            this.this_month_money = this_month_money;
        }

        public String getLast_month_money() {
            return last_month_money;
        }

        public void setLast_month_money(String last_month_money) {
            this.last_month_money = last_month_money;
        }

        public String getAll_price() {
            return all_price;
        }

        public void setAll_price(String all_price) {
            this.all_price = all_price;
        }

        public String getYesterday_counts() {
            return yesterday_counts;
        }

        public void setYesterday_counts(String yesterday_counts) {
            this.yesterday_counts = yesterday_counts;
        }

        public String getYesterday_money() {
            return yesterday_money;
        }

        public void setYesterday_money(String yesterday_money) {
            this.yesterday_money = yesterday_money;
        }

        public String getBefore_yesterday_counts() {
            return before_yesterday_counts;
        }

        public void setBefore_yesterday_counts(String before_yesterday_counts) {
            this.before_yesterday_counts = before_yesterday_counts;
        }

        public String getBefore_yesterday_money() {
            return before_yesterday_money;
        }

        public void setBefore_yesterday_money(String before_yesterday_money) {
            this.before_yesterday_money = before_yesterday_money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}

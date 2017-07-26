package com.daili.tsapp.jsBean.netBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 * 根据日期进行分组的界面的所有的数据
 */
public class FormlistDateBean implements Serializable {


    /**
     * flag : Success
     * msg : 获取成功
     * data : [{"today_order":null,"yesterday_order":[{"id":"102","order_num":"1490341602","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-24 15:46:42","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-24","order_acceptance_type":null,"order_id":"166","waiter_id":"71","datetime":"2017-03-24 15:46:42","get_order_date":"2017-03-24"},{"id":"103","order_num":"1490341908","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-24 15:51:48","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-24","order_acceptance_type":null,"order_id":"167","waiter_id":"71","datetime":"2017-03-24 15:51:48","get_order_date":"2017-03-24"},{"id":"104","order_num":"1490342169","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-24 15:56:09","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-24","order_acceptance_type":null,"order_id":"168","waiter_id":"71","datetime":"2017-03-24 15:56:09","get_order_date":"2017-03-24"}],"before_yesterday_order":null,"all_order":[{"id":"102","order_num":"1490341602","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-24 15:46:42","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-24","order_acceptance_type":null,"order_id":"166","waiter_id":"71","datetime":"2017-03-24 15:46:42","get_order_date":"2017-03-24"},{"id":"103","order_num":"1490341908","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-24 15:51:48","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-24","order_acceptance_type":null,"order_id":"167","waiter_id":"71","datetime":"2017-03-24 15:51:48","get_order_date":"2017-03-24"},{"id":"104","order_num":"1490342169","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-24 15:56:09","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-24","order_acceptance_type":null,"order_id":"168","waiter_id":"71","datetime":"2017-03-24 15:56:09","get_order_date":"2017-03-24"}]}]
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

    public static class DataBean implements Serializable {


        private List<OrderBean> today_order;
        private List<OrderBean> before_yesterday_order;
        private List<OrderBean> yesterday_order;
        private List<OrderBean> all_order;

        public List<OrderBean> getToday_order() {
            return today_order;
        }

        public void setToday_order(List<OrderBean> today_order1) {
            this.today_order = today_order;
        }

        public List<OrderBean> getBefore_yesterday_order() {
            return before_yesterday_order;
        }

        public void setBefore_yesterday_order(List<OrderBean> before_yesterday_order) {
            this.before_yesterday_order = before_yesterday_order;
        }

        public List<OrderBean> getYesterday_order() {
            return yesterday_order;
        }

        public void setYesterday_order(List<OrderBean> yesterday_order) {
            this.yesterday_order = yesterday_order;
        }

        public List<OrderBean> getAll_order() {
            return all_order;
        }

        public void setAll_order(List<OrderBean> all_order) {
            this.all_order = all_order;
        }

        public static class OrderBean implements Serializable {
            private String order_id;
            private String order_num;
            private String who_put_order;
            private String order_name;
            private String order_picture;
            private String order_put_form;
            private String order_price;
            private String order_wait_pay;
            private String order_put_time;
            private  String order_type;
            private  String order_minor_term_count;
            private List<OwnFormsBean.DataBean.OrderMinorTermNavBean> order_minor_term_nav;

            public List<OwnFormsBean.DataBean.OrderMinorTermNavBean> getOrder_minor_term_nav() {
                return order_minor_term_nav;
            }

            public void setOrder_minor_term_nav(List<OwnFormsBean.DataBean.OrderMinorTermNavBean> order_minor_term_nav) {
                this.order_minor_term_nav = order_minor_term_nav;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public String getOrder_minor_term_count() {
                return order_minor_term_count;
            }

            public void setOrder_minor_term_count(String order_minor_term_count) {
                this.order_minor_term_count = order_minor_term_count;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_put_form() {
                return order_put_form;
            }

            public void setOrder_put_form(String order_put_form) {
                this.order_put_form = order_put_form;
            }

            public String getOrder_put_time() {
                return order_put_time;
            }

            public void setOrder_put_time(String order_put_time) {
                this.order_put_time = order_put_time;
            }

            public String getOrder_picture() {
                return order_picture;
            }

            public void setOrder_picture(String order_picture) {
                this.order_picture = order_picture;
            }


            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getWho_put_order() {
                return who_put_order;
            }

            public void setWho_put_order(String who_put_order) {
                this.who_put_order = who_put_order;
            }

            public String getOrder_name() {
                return order_name;
            }

            public void setOrder_name(String order_name) {
                this.order_name = order_name;
            }


            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }


            public String getOrder_wait_pay() {
                return order_wait_pay;
            }

            public void setOrder_wait_pay(String order_wait_pay) {
                this.order_wait_pay = order_wait_pay;
            }

        }
    }
}

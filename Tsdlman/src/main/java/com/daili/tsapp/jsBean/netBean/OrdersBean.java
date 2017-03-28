package com.daili.tsapp.jsBean.netBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 * 存放待评价 已评价 已接单的所有的订单的数据
 */
public class OrdersBean implements Serializable{
    /**
     * flag : Success
     * msg : 数据获取陈宫
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
         * waiter_receiving : [{"id":"157","order_num":"1490606640","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-27 17:24:00","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-27","order_acceptance_type":"1","order_id":"222","waiter_id":"71","datetime":"2017-03-27 17:24:00","get_order_date":"2017-03-27"}]
         * have_evaluate : [{"id":"14","order_num":"1490606640","who_put_order":"18266142739","order_name":"等待确认","order_pic":"/Public/Home/img/moren.png","order_types":"等待确认","order_price":"597","order_wait_taking":"1","order_wait_pay":"1","order_time":"2017-03-27 17:24:00","order_always_person":null,"order_qiye_name":null,"order_qiye_address":null,"order_qiye_phone":null,"order_ask_who":null,"order_ask_phone":null,"order_ask_mail":null,"order_qiye_yingyezhizhao":null,"order_always_personal":null,"order_personal_name":"等待确认","order_personal_id_card":"等待确认","order_personal_tel":null,"order_personal_ask_preson":null,"order_personal_ask_tel":null,"order_personal_id_card_pic":"/Public/Home/img/moren.png","order_personal_getizhizhao":"/Public/Home/img/moren.png","order_type":"普通注册","belong_provice":null,"belong_city":null,"belong_county":null,"order_date_day":"2017-03-27","order_acceptance_type":"1","waiter_id":"71","user_id":"8","waiter_evaluate":"服务超棒！赞一个！","evaluate_type":"1"}]
         * nohave_evaluate : null
         */

        private   List<OrderBean> nohave_evaluate;
        private List<OrderBean> waiter_receiving;
        private List<OrderBean> have_evaluate;

        public List<OrderBean> getNohave_evaluate() {
            return nohave_evaluate;
        }

        public void setNohave_evaluate(List<OrderBean> nohave_evaluate) {
            this.nohave_evaluate = nohave_evaluate;
        }

        public List<OrderBean> getWaiter_receiving() {
            return waiter_receiving;
        }

        public void setWaiter_receiving(List<OrderBean> waiter_receiving) {
            this.waiter_receiving = waiter_receiving;
        }

        public List<OrderBean> getHave_evaluate() {
            return have_evaluate;
        }

        public void setHave_evaluate(List<OrderBean> have_evaluate) {
            this.have_evaluate = have_evaluate;
        }

        public static class OrderBean implements Serializable{
            /**
             * id : 157
             * order_num : 1490606640
             * who_put_order : 18266142739
             * order_name : 等待确认
             * order_pic : /Public/Home/img/moren.png
             * order_types : 等待确认
             * order_price : 597
             * order_wait_taking : 1
             * order_wait_pay : 1
             * order_time : 2017-03-27 17:24:00
             * order_always_person : null
             * order_qiye_name : null
             * order_qiye_address : null
             * order_qiye_phone : null
             * order_ask_who : null
             * order_ask_phone : null
             * order_ask_mail : null
             * order_qiye_yingyezhizhao : null
             * order_always_personal : null
             * order_personal_name : 等待确认
             * order_personal_id_card : 等待确认
             * order_personal_tel : null
             * order_personal_ask_preson : null
             * order_personal_ask_tel : null
             * order_personal_id_card_pic : /Public/Home/img/moren.png
             * order_personal_getizhizhao : /Public/Home/img/moren.png
             * order_type : 普通注册
             * belong_provice : null
             * belong_city : null
             * belong_county : null
             * order_date_day : 2017-03-27
             * order_acceptance_type : 1
             * order_id : 222
             * waiter_id : 71
             * datetime : 2017-03-27 17:24:00
             * get_order_date : 2017-03-27
             */

            private String id;
            private String order_num;
            private String who_put_order;
            private String order_name;
            private String order_pic;
            private String order_types;
            private String order_price;
            private String order_wait_taking;
            private String order_wait_pay;
            private String order_time;
            private Object order_always_person;
            private Object order_qiye_name;
            private Object order_qiye_address;
            private Object order_qiye_phone;
            private Object order_ask_who;
            private Object order_ask_phone;
            private Object order_ask_mail;
            private Object order_qiye_yingyezhizhao;
            private Object order_always_personal;
            private String order_personal_name;
            private String order_personal_id_card;
            private Object order_personal_tel;
            private Object order_personal_ask_preson;
            private Object order_personal_ask_tel;
            private String order_personal_id_card_pic;
            private String order_personal_getizhizhao;
            private String order_type;
            private Object belong_provice;
            private Object belong_city;
            private Object belong_county;
            private String order_date_day;
            private String order_acceptance_type;
            private String order_id;
            private String waiter_id;
            private String datetime;
            private String get_order_date;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getOrder_pic() {
                return order_pic;
            }

            public void setOrder_pic(String order_pic) {
                this.order_pic = order_pic;
            }

            public String getOrder_types() {
                return order_types;
            }

            public void setOrder_types(String order_types) {
                this.order_types = order_types;
            }

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public String getOrder_wait_taking() {
                return order_wait_taking;
            }

            public void setOrder_wait_taking(String order_wait_taking) {
                this.order_wait_taking = order_wait_taking;
            }

            public String getOrder_wait_pay() {
                return order_wait_pay;
            }

            public void setOrder_wait_pay(String order_wait_pay) {
                this.order_wait_pay = order_wait_pay;
            }

            public String getOrder_time() {
                return order_time;
            }

            public void setOrder_time(String order_time) {
                this.order_time = order_time;
            }

            public Object getOrder_always_person() {
                return order_always_person;
            }

            public void setOrder_always_person(Object order_always_person) {
                this.order_always_person = order_always_person;
            }

            public Object getOrder_qiye_name() {
                return order_qiye_name;
            }

            public void setOrder_qiye_name(Object order_qiye_name) {
                this.order_qiye_name = order_qiye_name;
            }

            public Object getOrder_qiye_address() {
                return order_qiye_address;
            }

            public void setOrder_qiye_address(Object order_qiye_address) {
                this.order_qiye_address = order_qiye_address;
            }

            public Object getOrder_qiye_phone() {
                return order_qiye_phone;
            }

            public void setOrder_qiye_phone(Object order_qiye_phone) {
                this.order_qiye_phone = order_qiye_phone;
            }

            public Object getOrder_ask_who() {
                return order_ask_who;
            }

            public void setOrder_ask_who(Object order_ask_who) {
                this.order_ask_who = order_ask_who;
            }

            public Object getOrder_ask_phone() {
                return order_ask_phone;
            }

            public void setOrder_ask_phone(Object order_ask_phone) {
                this.order_ask_phone = order_ask_phone;
            }

            public Object getOrder_ask_mail() {
                return order_ask_mail;
            }

            public void setOrder_ask_mail(Object order_ask_mail) {
                this.order_ask_mail = order_ask_mail;
            }

            public Object getOrder_qiye_yingyezhizhao() {
                return order_qiye_yingyezhizhao;
            }

            public void setOrder_qiye_yingyezhizhao(Object order_qiye_yingyezhizhao) {
                this.order_qiye_yingyezhizhao = order_qiye_yingyezhizhao;
            }

            public Object getOrder_always_personal() {
                return order_always_personal;
            }

            public void setOrder_always_personal(Object order_always_personal) {
                this.order_always_personal = order_always_personal;
            }

            public String getOrder_personal_name() {
                return order_personal_name;
            }

            public void setOrder_personal_name(String order_personal_name) {
                this.order_personal_name = order_personal_name;
            }

            public String getOrder_personal_id_card() {
                return order_personal_id_card;
            }

            public void setOrder_personal_id_card(String order_personal_id_card) {
                this.order_personal_id_card = order_personal_id_card;
            }

            public Object getOrder_personal_tel() {
                return order_personal_tel;
            }

            public void setOrder_personal_tel(Object order_personal_tel) {
                this.order_personal_tel = order_personal_tel;
            }

            public Object getOrder_personal_ask_preson() {
                return order_personal_ask_preson;
            }

            public void setOrder_personal_ask_preson(Object order_personal_ask_preson) {
                this.order_personal_ask_preson = order_personal_ask_preson;
            }

            public Object getOrder_personal_ask_tel() {
                return order_personal_ask_tel;
            }

            public void setOrder_personal_ask_tel(Object order_personal_ask_tel) {
                this.order_personal_ask_tel = order_personal_ask_tel;
            }

            public String getOrder_personal_id_card_pic() {
                return order_personal_id_card_pic;
            }

            public void setOrder_personal_id_card_pic(String order_personal_id_card_pic) {
                this.order_personal_id_card_pic = order_personal_id_card_pic;
            }

            public String getOrder_personal_getizhizhao() {
                return order_personal_getizhizhao;
            }

            public void setOrder_personal_getizhizhao(String order_personal_getizhizhao) {
                this.order_personal_getizhizhao = order_personal_getizhizhao;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public Object getBelong_provice() {
                return belong_provice;
            }

            public void setBelong_provice(Object belong_provice) {
                this.belong_provice = belong_provice;
            }

            public Object getBelong_city() {
                return belong_city;
            }

            public void setBelong_city(Object belong_city) {
                this.belong_city = belong_city;
            }

            public Object getBelong_county() {
                return belong_county;
            }

            public void setBelong_county(Object belong_county) {
                this.belong_county = belong_county;
            }

            public String getOrder_date_day() {
                return order_date_day;
            }

            public void setOrder_date_day(String order_date_day) {
                this.order_date_day = order_date_day;
            }

            public String getOrder_acceptance_type() {
                return order_acceptance_type;
            }

            public void setOrder_acceptance_type(String order_acceptance_type) {
                this.order_acceptance_type = order_acceptance_type;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getWaiter_id() {
                return waiter_id;
            }

            public void setWaiter_id(String waiter_id) {
                this.waiter_id = waiter_id;
            }

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getGet_order_date() {
                return get_order_date;
            }

            public void setGet_order_date(String get_order_date) {
                this.get_order_date = get_order_date;
            }
        }
    }
}

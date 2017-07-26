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
     * msg : 数据获取成功
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

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public List<OwnFormsBean.DataBean.OrderMinorTermNavBean> getOrder_minor_term_nav() {
                return order_minor_term_nav;
            }

            public void setOrder_minor_term_nav(List<OwnFormsBean.DataBean.OrderMinorTermNavBean> order_minor_term_nav) {
                this.order_minor_term_nav = order_minor_term_nav;
            }

            public String getOrder_picture() {
                return order_picture;
            }

            public void setOrder_picture(String order_picture) {
                this.order_picture = order_picture;
            }

            public String getOrder_minor_term_count() {
                return order_minor_term_count;
            }

            public void setOrder_minor_term_count(String order_minor_term_count) {
                this.order_minor_term_count = order_minor_term_count;
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

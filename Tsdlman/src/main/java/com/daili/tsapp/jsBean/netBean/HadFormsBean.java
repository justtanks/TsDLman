//package com.daili.tsapp.jsBean.netBean;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2017/3/9.
// */
//
//public class HadFormsBean {
//
//    /**
//     * flag : Success
//     * msg : 1
//     * data : [{"order_id":"126","order_num":"1489037441","who_put_order":"18266142739","shangbiao_name":"哈哈约车","
//     * order_pic":"/Uploads/2017-03-09/58c0e881de4aa.jpg","order_types":"第01类　　化学原料第31类　　饲料种籽第35类　　
//     * 广告销售第42类　　科技服务","order_price":"4400","order_wait_pay":"1","order_time":"2017-03-09 13:30:41","order_always_person":null,"
//     * order_qiye_name":"10369","order_qiye_address":"山东省德州市开发区康博公关A座22楼","order_qiye_phone":"18266142739","order_ask_who":"朱堃罡",
//     * "order_ask_phone":"18266142739","order_ask_mail":"154496959@qq.com","order_qiye_yingyezhizhao":"/Uploads/2017-03-09/58c0e881e031d.jpg",
//     * "order_acceptance_type":[{"order_type":"1"},{"order_type":"2"},{"order_type":"3"},{"order_type":"4"},{"order_type":"5"},
//     * {"order_type":"6"}],"order_type":"企业注册"}]
//     * num : 1
//     */
//
//    private String flag;
//    private int msg;
//    private int num;
//    private List<DataBean> data;
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
//
//    public int getMsg() {
//        return msg;
//    }
//
//    public void setMsg(int msg) {
//        this.msg = msg;
//    }
//
//    public int getNum() {
//        return num;
//    }
//
//    public void setNum(int num) {
//        this.num = num;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * order_id : 126
//         * order_num : 1489037441
//         * who_put_order : 18266142739
//         * shangbiao_name : 哈哈约车
//         * order_pic : /Uploads/2017-03-09/58c0e881de4aa.jpg
//         * order_types : 第01类　　化学原料第31类　　饲料种籽第35类　　广告销售第42类　　科技服务
//         * order_price : 4400
//         * order_wait_pay : 1
//         * order_time : 2017-03-09 13:30:41
//         * order_always_person : null
//         * order_qiye_name : 10369
//         * order_qiye_address : 山东省德州市开发区康博公关A座22楼
//         * order_qiye_phone : 18266142739
//         * order_ask_who : 朱堃罡
//         * order_ask_phone : 18266142739
//         * order_ask_mail : 154496959@qq.com
//         * order_qiye_yingyezhizhao : /Uploads/2017-03-09/58c0e881e031d.jpg
//         * order_acceptance_type : [{"order_type":"1"},{"order_type":"2"},{"order_type":"3"},{"order_type":"4"},{"order_type":"5"},{"order_type":"6"}]
//         * order_type : 企业注册
//         */
//
//        private String order_id;
//        private String order_num;
//        private String who_put_order;
//        private String shangbiao_name;
//        private String order_pic;
//        private String order_types;
//        private String order_price;
//        private String order_wait_pay;
//        private String order_time;
//        private Object order_always_person;
//        private String order_qiye_name;
//        private String order_qiye_address;
//        private String order_qiye_phone;
//        private String order_ask_who;
//        private String order_ask_phone;
//        private String order_ask_mail;
//        private String order_qiye_yingyezhizhao;
//        private String order_type;
//        private List<OrderAcceptanceTypeBean> order_acceptance_type;
//
//        public String getOrder_id() {
//            return order_id;
//        }
//
//        public void setOrder_id(String order_id) {
//            this.order_id = order_id;
//        }
//
//        public String getOrder_num() {
//            return order_num;
//        }
//
//        public void setOrder_num(String order_num) {
//            this.order_num = order_num;
//        }
//
//        public String getWho_put_order() {
//            return who_put_order;
//        }
//
//        public void setWho_put_order(String who_put_order) {
//            this.who_put_order = who_put_order;
//        }
//
//        public String getShangbiao_name() {
//            return shangbiao_name;
//        }
//
//        public void setShangbiao_name(String shangbiao_name) {
//            this.shangbiao_name = shangbiao_name;
//        }
//
//        public String getOrder_pic() {
//            return order_pic;
//        }
//
//        public void setOrder_pic(String order_pic) {
//            this.order_pic = order_pic;
//        }
//
//        public String getOrder_types() {
//            return order_types;
//        }
//
//        public void setOrder_types(String order_types) {
//            this.order_types = order_types;
//        }
//
//        public String getOrder_price() {
//            return order_price;
//        }
//
//        public void setOrder_price(String order_price) {
//            this.order_price = order_price;
//        }
//
//        public String getOrder_wait_pay() {
//            return order_wait_pay;
//        }
//
//        public void setOrder_wait_pay(String order_wait_pay) {
//            this.order_wait_pay = order_wait_pay;
//        }
//
//        public String getOrder_time() {
//            return order_time;
//        }
//
//        public void setOrder_time(String order_time) {
//            this.order_time = order_time;
//        }
//
//        public Object getOrder_always_person() {
//            return order_always_person;
//        }
//
//        public void setOrder_always_person(Object order_always_person) {
//            this.order_always_person = order_always_person;
//        }
//
//        public String getOrder_qiye_name() {
//            return order_qiye_name;
//        }
//
//        public void setOrder_qiye_name(String order_qiye_name) {
//            this.order_qiye_name = order_qiye_name;
//        }
//
//        public String getOrder_qiye_address() {
//            return order_qiye_address;
//        }
//
//        public void setOrder_qiye_address(String order_qiye_address) {
//            this.order_qiye_address = order_qiye_address;
//        }
//
//        public String getOrder_qiye_phone() {
//            return order_qiye_phone;
//        }
//
//        public void setOrder_qiye_phone(String order_qiye_phone) {
//            this.order_qiye_phone = order_qiye_phone;
//        }
//
//        public String getOrder_ask_who() {
//            return order_ask_who;
//        }
//
//        public void setOrder_ask_who(String order_ask_who) {
//            this.order_ask_who = order_ask_who;
//        }
//
//        public String getOrder_ask_phone() {
//            return order_ask_phone;
//        }
//
//        public void setOrder_ask_phone(String order_ask_phone) {
//            this.order_ask_phone = order_ask_phone;
//        }
//
//        public String getOrder_ask_mail() {
//            return order_ask_mail;
//        }
//
//        public void setOrder_ask_mail(String order_ask_mail) {
//            this.order_ask_mail = order_ask_mail;
//        }
//
//        public String getOrder_qiye_yingyezhizhao() {
//            return order_qiye_yingyezhizhao;
//        }
//
//        public void setOrder_qiye_yingyezhizhao(String order_qiye_yingyezhizhao) {
//            this.order_qiye_yingyezhizhao = order_qiye_yingyezhizhao;
//        }
//
//        public String getOrder_type() {
//            return order_type;
//        }
//
//        public void setOrder_type(String order_type) {
//            this.order_type = order_type;
//        }
//
//        public List<OrderAcceptanceTypeBean> getOrder_acceptance_type() {
//            return order_acceptance_type;
//        }
//
//        public void setOrder_acceptance_type(List<OrderAcceptanceTypeBean> order_acceptance_type) {
//            this.order_acceptance_type = order_acceptance_type;
//        }
//
//        public static class OrderAcceptanceTypeBean {
//            /**
//             * order_type : 1
//             */
//
//            private String order_type;
//
//            public String getOrder_type() {
//                return order_type;
//            }
//
//            public void setOrder_type(String order_type) {
//                this.order_type = order_type;
//            }
//        }
//    }
//}

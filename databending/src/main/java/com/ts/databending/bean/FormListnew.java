package com.ts.databending.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 * 抢单页面订单列表
 */

public class FormListnew {

    /**
     * flag : Success
     * msg : 2
     * data : [{"order_id":"19","who_put_order":"18266142739","shangbiao_name":"123","order_pic":"
     * /Uploads/58742c19057d0.jpg","order_types":"第29类　　食品第30类　　方便食品第31类　　饲料种籽第32类　　
     * 啤酒饮料第33类　　酒第35类　　广告销售第43类　　餐饮住宿第37类　　建筑修理第40类　　材料加工第42类　　科技服务",
     * "order_price":"10450","order_wait_pay":"1","order_time":"2017-01-10 08:34:33","order_always_person":null,"order_qiye_name":"万正",
     * "order_qiye_address":"德州","order_qiye_phone":"18266142121","order_ask_who":"大王","order_ask_phone":"18266142739","order_ask_mail":
     * "154496959@qq.com","order_qiye_yingyezhizhao":"/Uploads/58742c1906636.jpg","order_type":"企业注册"},{"order_id":"20","who_put_order":
     * "18266142739","shangbiao_name":"123","order_pic":"/Uploads/5874355367ec1.jpg","order_types":"第09类　　科学仪器第16类　　办公用品第35类　
     * 　广告销售第39类　　运输贮藏第41类　　教育娱乐第43类　　餐饮住宿第45类　　社会服务","order_price":"7700","order_wait_pay":"1","order_time":
     * "2017-01-10 09:13:55","order_always_personal":"123","order_personal_name":"123","order_personal_id_card":"123","order_personal_tel":"123","
     * order_personal_ask_preson":"123","order_personal_ask_tel":"123","order_personal_id_card_pic":"/Uploads/587435536910b.jpg","order_personal_getizhizhao":
     * "/Uploads/587435536ac41.png","order_type":"个人注册"}]
     * num : 1
     */

    private String flag;
    private int msg;
    private int num;
    private List<DataBean> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
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
    @Table(name = "myform")
    public static class DataBean {
        /**
         * order_id : 19
         * who_put_order : 18266142739
         * shangbiao_name : 123
         * order_pic : /Uploads/58742c19057d0.jpg
         * order_types : 第29类　　食品第30类　　方便食品第31类　　饲料种籽第32类　　啤酒饮料第33类　　酒第35类　　广告销售第43类　　餐饮住宿第37类　　建筑修理第40类　　材料加工第42类　　科技服务
         * order_price : 10450
         * order_wait_pay : 1
         * order_time : 2017-01-10 08:34:33
         * order_always_person : null
         * order_qiye_name : 万正
         * order_qiye_address : 德州
         * order_qiye_phone : 18266142121
         * order_ask_who : 大王
         * order_ask_phone : 18266142739
         * order_ask_mail : 154496959@qq.com
         * order_qiye_yingyezhizhao : /Uploads/58742c1906636.jpg
         * order_type : 企业注册
         * order_always_personal : 123
         * order_personal_name : 123
         * order_personal_id_card : 123
         * order_personal_tel : 123
         * order_personal_ask_preson : 123
         * order_personal_ask_tel : 123
         * order_personal_id_card_pic : /Uploads/587435536910b.jpg
         * order_personal_getizhizhao : /Uploads/587435536ac41.png
         */

        @Column(name = "id",isId = true)
        private int id;
        @Column(name = "order_id",property = "UNIQUE")
        private String order_id;
        @Column(name = "who_put_order")
        private String who_put_order;
        @Column(name = "shangbiao_name")
        private String shangbiao_name;
        @Column(name = "order_pic")
        private String order_pic;
        @Column(name = "order_types")
        private String order_types;
        @Column(name = "order_price")
        private String order_price;
        @Column(name = "order_wait_pay")
        private String order_wait_pay;
        @Column(name = "order_time")
        private String order_time;
        @Column(name = "order_always_person")
        private Object order_always_person;
        @Column(name = "order_qiye_name")
        private String order_qiye_name;
        @Column(name = "order_qiye_address")
        private String order_qiye_address;
        @Column(name = "order_qiye_phone")
        private String order_qiye_phone;
        @Column(name = "order_ask_who")
        private String order_ask_who;
        @Column(name = "order_ask_phone")
        private String order_ask_phone;
        @Column(name = "order_ask_mail")
        private String order_ask_mail;
        @Column(name = "order_qiye_yingyezhizhao")
        private String order_qiye_yingyezhizhao;
        @Column(name = "order_type")
        private String order_type;
        @Column(name = "order_always_personal")
        private String order_always_personal;
        @Column(name = "order_personal_name")
        private String order_personal_name;
        @Column(name = "order_personal_id_card")
        private String order_personal_id_card;
        @Column(name = "order_personal_tel")
        private String order_personal_tel;
        @Column(name = "order_personal_ask_preson")
        private String order_personal_ask_preson;
        @Column(name = "order_personal_ask_tel")
        private String order_personal_ask_tel;
        @Column(name = "order_personal_id_card_pic")
        private String order_personal_id_card_pic;
        @Column(name = "order_personal_getizhizhao")
        private String order_personal_getizhizhao;
        @Column(name = "order_num")
        private String order_num;

        @BindingAdapter("order_pic")
        public static  void loadInternetImage(ImageView image,String order_pic){
            Picasso.with(image.getContext()).load(order_pic).into(image);
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getWho_put_order() {
            return who_put_order;
        }

        public void setWho_put_order(String who_put_order) {
            this.who_put_order = who_put_order;
        }

        public String getShangbiao_name() {
            return shangbiao_name;
        }

        public void setShangbiao_name(String shangbiao_name) {
            this.shangbiao_name = shangbiao_name;
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

        public String getOrder_qiye_name() {
            return order_qiye_name;
        }

        public void setOrder_qiye_name(String order_qiye_name) {
            this.order_qiye_name = order_qiye_name;
        }

        public String getOrder_qiye_address() {
            return order_qiye_address;
        }

        public void setOrder_qiye_address(String order_qiye_address) {
            this.order_qiye_address = order_qiye_address;
        }

        public String getOrder_qiye_phone() {
            return order_qiye_phone;
        }

        public void setOrder_qiye_phone(String order_qiye_phone) {
            this.order_qiye_phone = order_qiye_phone;
        }

        public String getOrder_ask_who() {
            return order_ask_who;
        }

        public void setOrder_ask_who(String order_ask_who) {
            this.order_ask_who = order_ask_who;
        }

        public String getOrder_ask_phone() {
            return order_ask_phone;
        }

        public void setOrder_ask_phone(String order_ask_phone) {
            this.order_ask_phone = order_ask_phone;
        }

        public String getOrder_ask_mail() {
            return order_ask_mail;
        }

        public void setOrder_ask_mail(String order_ask_mail) {
            this.order_ask_mail = order_ask_mail;
        }

        public String getOrder_qiye_yingyezhizhao() {
            return order_qiye_yingyezhizhao;
        }

        public void setOrder_qiye_yingyezhizhao(String order_qiye_yingyezhizhao) {
            this.order_qiye_yingyezhizhao = order_qiye_yingyezhizhao;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getOrder_always_personal() {
            return order_always_personal;
        }

        public void setOrder_always_personal(String order_always_personal) {
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

        public String getOrder_personal_tel() {
            return order_personal_tel;
        }

        public void setOrder_personal_tel(String order_personal_tel) {
            this.order_personal_tel = order_personal_tel;
        }

        public String getOrder_personal_ask_preson() {
            return order_personal_ask_preson;
        }

        public void setOrder_personal_ask_preson(String order_personal_ask_preson) {
            this.order_personal_ask_preson = order_personal_ask_preson;
        }

        public String getOrder_personal_ask_tel() {
            return order_personal_ask_tel;
        }

        public void setOrder_personal_ask_tel(String order_personal_ask_tel) {
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
    }
}

package com.daili.tsapp.jsBean.netBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class LoginBean2 {

    /**
     * flag : Success
     * msg : 密码正确
     * data : [{"waiter_id":"70","waiter_name":"shao","waiter_sex":"1","waiter_id_card":"22222222","waiter_phone_number":"15621295399",
     * "waiter_pic":"./Uploads/touxiang/15621295399.jpg","waiter_address":"山东省德州市","waiter_work_time":"1","waiter_good_work":"27","is_renzheng":"0"}]
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
   @Table(name = "userinfo")
    public static class DataBean {
        /**
         * waiter_id : 70
         * waiter_name : shao
         * waiter_sex : 1
         * waiter_id_card : 22222222
         * waiter_phone_number : 15621295399
         * waiter_pic : ./Uploads/touxiang/15621295399.jpg
         * waiter_address : 山东省德州市
         * waiter_work_time : 1
         * waiter_good_work : 27
         * is_renzheng : 0
         */
        @Column(name = "id",isId = true)
        private  int id;
       @Column(name = "waiter_id",property = "UNIQUE")
        private String waiter_id;
       @Column(name = "waiter_name")
        private String waiter_name;
       @Column(name = "waiter_sex")
        private String waiter_sex;
       @Column(name = "waiter_id_card")
        private String waiter_id_card;
       @Column(name = "waiter_phone_number")
        private String waiter_phone_number;
       @Column(name = "waiter_pic")
        private String waiter_pic;
       @Column(name = "waiter_address")
        private String waiter_address;
       @Column(name = "waiter_work_time")
        private String waiter_work_time;
       @Column(name = "waiter_good_work")
        private String waiter_good_work;
       @Column(name = "is_renzheng")
        private String is_renzheng;
       @Column(name = "evaluate_num")
       private int evaluate_num;

       public int getEvaluate_num() {
           return evaluate_num;
       }

       public void setEvaluate_num(int evaluate_num) {
           this.evaluate_num = evaluate_num;
       }

       public String getWaiter_id() {
            return waiter_id;
        }

        public void setWaiter_id(String waiter_id) {
            this.waiter_id = waiter_id;
        }

        public String getWaiter_name() {
            return waiter_name;
        }

        public void setWaiter_name(String waiter_name) {
            this.waiter_name = waiter_name;
        }

        public String getWaiter_sex() {
            return waiter_sex;
        }

        public void setWaiter_sex(String waiter_sex) {
            this.waiter_sex = waiter_sex;
        }

        public String getWaiter_id_card() {
            return waiter_id_card;
        }

        public void setWaiter_id_card(String waiter_id_card) {
            this.waiter_id_card = waiter_id_card;
        }

        public String getWaiter_phone_number() {
            return waiter_phone_number;
        }

        public void setWaiter_phone_number(String waiter_phone_number) {
            this.waiter_phone_number = waiter_phone_number;
        }

        public String getWaiter_pic() {
            return waiter_pic;
        }

        public void setWaiter_pic(String waiter_pic) {
            this.waiter_pic = waiter_pic;
        }

        public String getWaiter_address() {
            return waiter_address;
        }

        public void setWaiter_address(String waiter_address) {
            this.waiter_address = waiter_address;
        }

        public String getWaiter_work_time() {
            return waiter_work_time;
        }

        public void setWaiter_work_time(String waiter_work_time) {
            this.waiter_work_time = waiter_work_time;
        }

        public String getWaiter_good_work() {
            return waiter_good_work;
        }

        public void setWaiter_good_work(String waiter_good_work) {
            this.waiter_good_work = waiter_good_work;
        }

        public String getIs_renzheng() {
            return is_renzheng;
        }

        public void setIs_renzheng(String is_renzheng) {
            this.is_renzheng = is_renzheng;
        }

       @Override
       public String toString() {
           return "DataBean{" +
                   "id=" + id +
                   ", waiter_id='" + waiter_id + '\'' +
                   ", waiter_name='" + waiter_name + '\'' +
                   ", waiter_sex='" + waiter_sex + '\'' +
                   ", waiter_id_card='" + waiter_id_card + '\'' +
                   ", waiter_phone_number='" + waiter_phone_number + '\'' +
                   ", waiter_pic='" + waiter_pic + '\'' +
                   ", waiter_address='" + waiter_address + '\'' +
                   ", waiter_work_time='" + waiter_work_time + '\'' +
                   ", waiter_good_work='" + waiter_good_work + '\'' +
                   ", is_renzheng='" + is_renzheng + '\'' +
                   '}';
       }
   }
}

package com.daili.tsapp.jsBean.netBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 * 评论列表bean
 */

public class PingLunBean implements Serializable {

    /**
     * flag : Success
     * msg : 0
     * data : [{"user_phone":"18266142739","evaluate_nav":"?????????","evaluate_type":"1","order_num":"1484702316"},
     * {"user_phone":"18266142739","evaluate_nav":"服务一般般吧！","evaluate_type":"2","order_num":"1484702316"},{"user_phone":"18266142739","
     * evaluate_nav":"态度恶劣！差评！","evaluate_type":"3","order_num":"1484702316"}]
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

    public static class DataBean implements Serializable{
        /**
         * user_phone : 18266142739
         * evaluate_nav : ?????????
         * evaluate_type : 1
         * order_num : 1484702316
         */

        private String user_phone;
        private String evaluate_nav;
        private String evaluate_type;
        private String order_num;

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getEvaluate_nav() {
            return evaluate_nav;
        }

        public void setEvaluate_nav(String evaluate_nav) {
            this.evaluate_nav = evaluate_nav;
        }

        public String getEvaluate_type() {
            return evaluate_type;
        }

        public void setEvaluate_type(String evaluate_type) {
            this.evaluate_type = evaluate_type;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }
    }
}

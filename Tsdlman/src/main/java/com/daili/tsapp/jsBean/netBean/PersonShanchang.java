package com.daili.tsapp.jsBean.netBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class PersonShanchang implements Serializable {

    /**
    * 返回显示代理人个人擅长领域  然后根据数组进行判断显示所有的
     * 兴趣条目
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
         * good_work_id : 1
         * good_work : 普通注册
         */

        private int good_work_id;
        private String good_work;

        public int getGood_work_id() {
            return good_work_id;
        }

        public void setGood_work_id(int good_work_id) {
            this.good_work_id = good_work_id;
        }

        public String getGood_work() {
            return good_work;
        }

        public void setGood_work(String good_work) {
            this.good_work = good_work;
        }
    }
}

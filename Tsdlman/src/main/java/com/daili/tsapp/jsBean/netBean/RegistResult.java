package com.daili.tsapp.jsBean.netBean;


/**
 * Created by Administrator on 2017/1/10.
 */

public class RegistResult {

    /**
     * flag : Success
     * msg : 注册成功
     * data : 77
     * num : 1
     */

    private String flag;
    private String msg;
    private int data;
    private int num;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

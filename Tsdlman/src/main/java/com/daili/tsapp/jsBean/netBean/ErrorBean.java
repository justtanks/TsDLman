package com.daili.tsapp.jsBean.netBean;

/**
 * Created by Administrator on 2017/2/21.
 */

public class ErrorBean {
    /**
     * flag : Error
     * msg : 密码错误
     * num : 0
     */

    private String flag;
    private String msg;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

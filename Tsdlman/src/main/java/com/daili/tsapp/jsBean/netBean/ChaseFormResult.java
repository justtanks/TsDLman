package com.daili.tsapp.jsBean.netBean;

/**
 * Created by Administrator on 2017/1/12.
 * 抢单结果的返回
 *
 */

public class ChaseFormResult {

    /**
     * flag : Error
     * msg : 此单已经被抢了
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

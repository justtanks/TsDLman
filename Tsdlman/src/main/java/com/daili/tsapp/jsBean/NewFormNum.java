package com.daili.tsapp.jsBean;

/**
 * Created by Administrator on 2017/1/14.
 * 新订单的eventbus使用 封装数据传递
 *
 */

public class NewFormNum {
    private int num;

    public NewFormNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

package com.daili.tsapp.jsBean;

/**
 * Created by Administrator on 2017/7/8.
 * 区分推送不同的业务的实体类，用于Eventbus 传递事件
 *
 */

public class TuiSongBusBean {
    int ss;

    public TuiSongBusBean(int ss) {
        this.ss = ss;
    }

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }
}

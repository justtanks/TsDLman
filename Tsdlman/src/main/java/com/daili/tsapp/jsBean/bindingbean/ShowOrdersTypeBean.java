package com.daili.tsapp.jsBean.bindingbean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/25.
 */

public class ShowOrdersTypeBean {
    String todayTime;
    String danshu;
//    String shouyi;
    String yestodaytime;
    String beforyedaytime;

    public String getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(String todayTime) {
        this.todayTime = todayTime;
    }

    public String getYestodaytime() {
        return yestodaytime;
    }

    public void setYestodaytime(String yestodaytime) {
        this.yestodaytime = yestodaytime;
    }

    public String getBeforyedaytime() {
        return beforyedaytime;
    }

    public void setBeforyedaytime(String beforyedaytime) {
        this.beforyedaytime = beforyedaytime;
    }

    public ShowOrdersTypeBean(String danshu) {
        this.danshu = danshu;
//        this.shouyi = shouyi;
         getTodayTime();
    }
    private void getdayTime(){
        Date today = new Date();
        this.todayTime=new SimpleDateFormat("yyyy年MM月dd日:").format(today);
        Date yesterday = new Date(today.getTime() - 86400000L);
        this.yestodaytime= new SimpleDateFormat("yyyy年MM月dd日:").format(yesterday);
        Date beforeyestoday=new Date(yesterday.getTime()-86400000L);
        this.beforyedaytime=new SimpleDateFormat("yyyy年MM月dd日:").format(beforeyestoday);
    }
    public String getDanshu() {
        return danshu;
    }

    public void setDanshu(String danshu) {
        this.danshu = danshu;
    }

//    public String getShouyi() {
//    return shouyi;
//}
//
//    public void setShouyi(String shouyi) {
//        this.shouyi = shouyi;
//    }
}

package com.daili.tsapp.jsBean;

/**
 * Created by Administrator on 2017/3/7.
 * 在进度展示页面中展示expendlistview中childview的数据
 */

public class ExpendListChildBean {
    int childId;
    int belongid;
    String text;
    boolean ispressed;
    boolean canpress;

    public ExpendListChildBean(int childId, String text,int belongid) {
        this.childId = childId;
        this.text = text;
        this.belongid=belongid;
        this.ispressed = false;
        this.canpress = true;
    }

    public ExpendListChildBean() {
    }

    public int getBelongid() {
        return belongid;
    }

    public void setBelongid(int belongid) {
        this.belongid = belongid;
    }
    public int getChildId() {
        return childId;
    }
    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean ispressed() {
        return ispressed;
    }

    public void setIspressed(boolean ispressed) {
        this.ispressed = ispressed;
    }

    public boolean isCanpress() {
        return canpress;
    }

    public void setCanpress(boolean canpress) {
        this.canpress = canpress;
    }
}

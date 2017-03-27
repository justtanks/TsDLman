package com.daili.tsapp.jsBean;

import com.daili.tsapp.jsBean.ExpendListChildBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */

public class ExpendListGroupBean {
    int id;
    String text;
    List<ExpendListChildBean> beens=new ArrayList<>();

    public ExpendListGroupBean(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public List<ExpendListChildBean> getBeens() {
        return beens;
    }

    public void setBeens(List<ExpendListChildBean> beens) {
        this.beens = beens;
    }

    public ExpendListGroupBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}


package com.daili.tsapp.jsBean.daoBean;

/**
 * Created by Administrator on 2017/1/10.
 * 两个string
 */

public class RegistArea {
     String id;
    String  text;

    public RegistArea(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public RegistArea() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "RegistArea{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

package com.daili.tsapp.jsBean.daoBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/1/4.
 */
@Table(name = "person")
public class Testuser {
    @Column(name = "id",isId = true)
    private  int id;
    @Column(name = "name")
    private  String name;
    @Column(name = "age")
    private int age;
    @Column(name = "isborn")
    private  boolean isBorn;
    @Column(name = "otherid",property = "NOT NULL")    //设置属性非空
    private int otherid;

    public Testuser(int id, String name, int age, boolean isBorn) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isBorn = isBorn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isBorn() {
        return isBorn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBorn(boolean born) {
        isBorn = born;
    }

}

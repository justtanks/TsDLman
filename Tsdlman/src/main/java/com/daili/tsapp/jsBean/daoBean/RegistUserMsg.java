package com.daili.tsapp.jsBean.daoBean;

import android.graphics.Bitmap;

import com.daili.tsapp.utils.ImagePostUtils;

/**
 * Created by Administrator on 2017/1/9.
 * 注册时需要的信息的封装
 */

public class RegistUserMsg {
    String name;
    String persionId;
    String phoneNum;
    String password;
    String sex;
    String workTime;
//    String bestArea;
    String backMsg;
    String imageBase;
    //擅长的领域的id
//    String favouraeID;
//    String favouraText1;
//    String getFavouraText2;

//    public String getGetFavouraText2() {
//        return getFavouraText2;
//    }
//
//    public void setGetFavouraText2(String getFavouraText2) {
//        this.getFavouraText2 = getFavouraText2;
//    }

//    public String getFavouraeID() {
//        return favouraeID;
//    }
//
//    public void setFavouraeID(String favouraeID) {
//        this.favouraeID = favouraeID;
//    }

//    public String getFavouraText1() {
//        return favouraText1;
//    }
//
//    public void setFavouraText1(String favouraText) {
//        this.favouraText1 = favouraText;
//    }

    public void setImageBase(Bitmap image){
      this.imageBase= ImagePostUtils.bitmapToBase64(image);
    }
    public String getImageBase(){
        return imageBase;

    }

    public String getBackMsg() {
        return backMsg;
    }

    public void setBackMsg(String backMsg) {
        this.backMsg = backMsg;
    }

    public RegistUserMsg(String name, String persionId, String phoneNum, String password, String sex, String workTime, String bestArea) {
        this.name = name;
        this.persionId = persionId;
        this.phoneNum = phoneNum;
        this.password = password;
        this.sex = sex;
        this.workTime = workTime;
//        this.bestArea = bestArea;
    }

    public RegistUserMsg() {
    }

    public String getName() {
        return name;
    }

    public String getPersionId() {
        return persionId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getWorkTime() {
        return workTime;
    }

//    public String getBestArea() {
//        return bestArea;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersionId(String persionId) {
        this.persionId = persionId;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

//    public void setBestArea(String bestArea) {
//        this.bestArea = bestArea;
//    }

    @Override
    public String toString() {
        return "RegistUserMsg{" +
                "name='" + name + '\'' +
                ", persionId='" + persionId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", workTime='" + workTime + '\'' +
                ", bestArea='"  + '\'' +
                '}';
    }
}

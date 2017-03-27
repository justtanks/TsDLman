package com.daili.tsapp.jsBean;

/**
 * Created by Administrator on 2017/1/18.
 * eventbus传递语音播放的bean
 */

public class VoiceSpeek {
    int id;
    String context;

    public VoiceSpeek(int id, String context) {
        this.id = id;
        this.context = context;
    }


    public VoiceSpeek(String context) {
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

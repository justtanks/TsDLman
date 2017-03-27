package com.ts.databending.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.NetworkOnMainThreadException;
import android.widget.ImageView;

import com.ts.databending.BR;

/**
 * Created by Administrator on 2017/1/13.
 */

public class User extends BaseObservable{//实现数据的同步，需要继承接口，然后通过给get方法添加@Bindable注解实现,给set方法添加notifyPropertyChanged(BR.sex);
    String name;
    String age;
    String sex;
    boolean bol;



    public User(String name, String age, String sex) {

        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User() {
    }
    String  imageutl;

    public String getImageutl() {
        return imageutl;
    }

    public void setImageutl(String imageutl) {
        this.imageutl = imageutl;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
   @Bindable     //------------------------------------------操作一
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);//-------------------------操作而

    }
    @Bindable
    public boolean isBol() {
        return bol;
    }

    public void setBol(boolean bol) {
        this.bol = bol;
        notifyPropertyChanged(BR.bol);
    }
     @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }
}

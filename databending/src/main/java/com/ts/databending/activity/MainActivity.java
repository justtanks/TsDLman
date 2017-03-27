package com.ts.databending.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.common.net.HttpHeaders;
import com.ts.databending.R;
import com.ts.databending.bean.NewBeanByBinding;
import com.ts.databending.bean.User;



import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    User user;
    NewBeanByBinding binding1=new NewBeanByBinding();
    static int TTS_DATA_CHECK=1;
    private TextToSpeech tts = null;


    private boolean ttsIsInit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里hello这个类是在data中设置class属性之后返回的类  直接进行设置就行，这里设置了类的包名
        com.ts.databending.activity.Hello binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
         user=new User();
        user.setAge(1+"");
        user.setName("tttttttttt");
        user.setSex("man");
        user.setBol(true);
        binding.setUser(user);//textview 使用
        binding.setBol(true);
        binding.setNum(11);
        binding.setStr("卡拉是条狗");
        ArrayList list=new ArrayList();
        list.add("strtsss");
        list.add("strtsss");
        list.add("strtsss");
        list.add("strtsss");
        binding.setHandler(this);//实现点击事件
        binding.setBean2(binding1);
        binding1.firstString.set("我去还要这么干");
        binding1.bolean.set(true);
        binding1.firstInt.set(222);
        //通过添加id 可以通过这种方式获取到控件
//        binding.extview.setOnClickListener(event->
//              Toast.makeText(this,",Toa",Toast.LENGTH_SHORT).show();
//        );

//        Intent intent=new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
//        startActivityForResult(intent, TTS_DATA_CHECK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==TTS_DATA_CHECK) {
            if(resultCode== TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts = new TextToSpeech(this,new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        if (status==TextToSpeech.SUCCESS) {
                            ttsIsInit = true;
                            if (tts.isLanguageAvailable(Locale.CHINA)>=0) {
                                tts.setPitch(0.8f);
                                tts.setSpeechRate(1.1f);
                                speak();
                            }
                        }
                    }

                    private void speak() {
                        if (tts!=null&&ttsIsInit) {
                            tts.speak("您有2个新的订单", TextToSpeech.QUEUE_ADD, null);
                        }
                    }
                });
            }else {
                Intent installAllVoice = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installAllVoice);
            }

        }
    }

    public void handerEvent(View view){
        Toast.makeText(view.getContext(),"click",Toast.LENGTH_SHORT).show();
         user.setSex("111111111111111");
        user.setName("22222222222222222222222");
        user.setAge("3333333333333333333333333333333333");
        user.setBol(false);//点击之后是按钮消失
        binding1.bolean.set(false);//有个错误
        binding1.firstString.set("hahaahhah");
        binding1.firstInt.set(10000);
        Intent intent=new Intent(this,ListViewActivity.class);
        startActivity(intent);
    }
}

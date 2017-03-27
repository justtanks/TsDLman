package com.ts.databending.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ts.databending.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SeconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secon);
        EventBus.getDefault().getStickyEvent(First.class);
    }

    @Subscribe
    protected void onStickyEvent(First first){
        // 这里实现你的逻辑即可, event即为传递过来的Event对象
        Toast.makeText(this,first.text,Toast.LENGTH_SHORT);
    }
    @Subscribe
    protected void receiveUser(First first){
        // 这里实现你的逻辑即可, event即为传递过来的Event对象
        Toast.makeText(this,first.text,Toast.LENGTH_SHORT);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

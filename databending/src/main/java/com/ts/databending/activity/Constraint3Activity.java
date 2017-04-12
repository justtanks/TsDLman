package com.ts.databending.activity;

import android.databinding.DataBindingUtil;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ts.databending.R;
import com.ts.databending.databinding.ActivityConstraint3Binding;
//databinding 和 constraintlayout 同时进行使用的效果非常好
public class Constraint3Activity extends AppCompatActivity {
     ActivityConstraint3Binding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_constraint3);
        b= DataBindingUtil.setContentView(this,R.layout.activity_constraint3);
        b.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Constraint3Activity.this, "hhhh", Toast.LENGTH_SHORT).show();
            }
        });
        b.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Constraint3Activity.this, "这下好玩了", Toast.LENGTH_SHORT).show();
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            }
        });

    }
}

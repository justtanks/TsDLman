package com.ts.databending.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ts.databending.R;
import com.ts.databending.databinding.HelloBinding2;

public class Main2Activity extends AppCompatActivity {
    HelloBinding2 b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}

package com.ts.databending.activity;

import android.view.View;
import android.widget.Toast;

/**
 *点击事件
 */

public class EventHandler {

    public void handerEvent(View view){
        Toast.makeText(view.getContext(),"click",Toast.LENGTH_SHORT).show();
    }
}

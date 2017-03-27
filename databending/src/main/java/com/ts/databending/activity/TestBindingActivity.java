package com.ts.databending.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.ts.databending.BR;
import com.ts.databending.R;

public class TestBindingActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       setContentView(R.layout.activity_test_binding);
        DataBindingUtil.setContentView(this,R.layout.activity_test_binding);
        init();

    }

    private void init() {
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
          Binding1Fragment fragment=new Binding1Fragment();
        transaction.add(R.id.fragment,fragment);
        transaction.commit();

    }


}

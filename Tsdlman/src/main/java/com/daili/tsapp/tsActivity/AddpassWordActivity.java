package com.daili.tsapp.tsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsView.PwdEditText;
import com.daili.tsapp.tsBase.BaseActivity;


/*
添加密码的界面
 */
public class AddpassWordActivity extends BaseActivity {
    PwdEditText text;
    TextView texback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpassword);
        init();
    }

    private void init() {
        text = (PwdEditText) this.findViewById(R.id.aaddpass_pet_pwd);
        texback = (TextView) this.findViewById(R.id.addpass_textback);
        texback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        text.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {

            @Override
            public void onInputFinish(String password) {
                Intent intent = new Intent(AddpassWordActivity.this, SurePasswordActivity.class);
                intent.putExtra("firstpass", password);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        text.setText(null);
    }
}


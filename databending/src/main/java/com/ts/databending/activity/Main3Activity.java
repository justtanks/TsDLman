package com.ts.databending.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.ts.databending.R;

import static java.security.AccessController.getContext;

//animation test for the property animation
public class Main3Activity extends AppCompatActivity {

    Button bt;
    Button bt2;
    Button bt3;
    int srwidth;
    int srheight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bt = (Button) findViewById(R.id.mybutton);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        WindowManager wm = getWindowManager();
        srwidth = wm.getDefaultDisplay().getWidth();
        srheight = wm.getDefaultDisplay().getHeight();
        Log.e("tag", "" + srwidth);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useObjectAnimation();
            }
        });
    }

    private void useObjectAnimation() {
        ObjectAnimator animation = ObjectAnimator.ofFloat(bt3, "translationX", 100f);
        animation.setDuration(1000);
        animation.start();
    }

    private void init() {
        ValueAnimator animation = ValueAnimator.ofInt(0, srwidth - 20 - bt2.getWidth());
        animation.setDuration(2000);
        animation.start();
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                int animatedValue = (int) updatedAnimation.getAnimatedValue();
                bt.setTranslationY(animatedValue);
                bt2.setTranslationX(animatedValue);
            }
        });

    }


}

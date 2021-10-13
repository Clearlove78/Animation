package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity{
    private Button start_btn,exit_btn;
    float curX = 0;
    float curY = 0;
    private AdaptiveIconDrawable animDance;
    float nextX = 0;
    float nextY = 0;
    float n = 0;
    float m = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_btn= (Button) findViewById(R.id.start_btn);
        exit_btn= (Button) findViewById(R.id.exit_btn);
        final ImageView imageView = (ImageView)findViewById(R.id.laoyangfly);
        final Handler handler = new Handler()
        {
            //get curX and curY and change location to make it down if image is out of screen, move it up
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what == 0x123)
                {
                    if(n < 1500 || m < -1) {
                        //move back if x > 500
                        if (nextX > 500) {
                            curX = nextX = 0;
                        } else {
                            nextX += 10;
                        }
                        //move image down
                        nextY = curY + (float) (Math.random() * 10 - 1);
                        n = curY;
                    }
                    //if n > 1500, move image up
                    if(n > 1500){
                        if(nextX < 0)
                        {
                            curX = nextX = 500;
                        }else {
                            nextX -= 10;
                        }
                        nextY = curY - (float)(Math.random() * 10 - 1);
                        m = curY;
                    }
                    //use animation to move image
                    TranslateAnimation anim = new TranslateAnimation(curX,nextX,curY,nextY);
                    curX = nextX;
                    curY = nextY;
                    anim.setDuration(200);
                    imageView.startAnimation(anim);
                }
            }
        };
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class) ;
                startActivity(intent);
            }
        });
        //Animation
        final AnimationDrawable butterfly = (AnimationDrawable) imageView.getBackground();
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                butterfly.start();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                },0,50);
            }
        });

    }
}


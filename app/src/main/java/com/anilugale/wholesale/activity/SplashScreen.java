package com.anilugale.wholesale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.anilugale.wholesale.R;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Timer timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },1000);
    }

    @Override
    public void onBackPressed() {

    }
}

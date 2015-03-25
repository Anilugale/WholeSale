package com.anilugale.wholesale.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.anilugale.wholesale.R;


public class Vendor extends ActionBarActivity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        sp=getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
/*
        if(!sp.getBoolean(Utility.Vendor,false))
        {
             startActivity(new Intent(this,VendorLogin.class));
        }*/
    }

    public void shop(View view) {





    }
}

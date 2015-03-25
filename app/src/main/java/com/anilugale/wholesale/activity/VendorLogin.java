package com.anilugale.wholesale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.anilugale.wholesale.R;

public class VendorLogin extends ActionBarActivity {

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);
    }


    public void login(View view) {

        if(valid())
        {
            Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean valid() {

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        if(username.getText().toString().trim().length()==0)
        {
            username.setError("Invalid Username");
            username.requestFocus();
            return false;
        }else if(password.getText().toString().trim().length()==0)
        {
            password.setError("Invalid Password");
            password.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }


    public void regi(View view) {

        startActivity(new Intent(this,VendorRegistration.class));
        finish();
    }
}

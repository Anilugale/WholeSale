package com.anilugale.wholesale.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.anilugale.wholesale.R;
import com.anilugale.wholesale.pojo.Vendor;
import com.anilugale.wholesale.util.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class VendorRegistration extends ActionBarActivity {

    EditText name,username,pass,contact;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);
        sp=getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
    }



    public void registration(View view) {

        if(valid())
        {

          JSONObject request=new JSONObject();
            try {
                request.put("contact",contact.getText().toString().trim());
                request.put("name",name.getText().toString().trim());
                request.put("username",username.getText().toString().trim());
                request.put("password",pass.getText().toString().trim());
                Toast.makeText(this,request.toString(),Toast.LENGTH_SHORT).show();

              final  ProgressDialog pd=ProgressDialog.show(this," ","Loading...",true,false);
                Utility.client.post(this,Utility.VendorRegister,new StringEntity(request.toString()),"application/json",new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        Log.e("response",new String(responseBody));
                        Type type=new TypeToken<Vendor>(){}.getType();
                        Gson gson=new Gson();
                        com.anilugale.wholesale.pojo.Vendor vendor=gson.fromJson(new String(responseBody),type);


                        if(vendor!=null) {
                            SharedPreferences.Editor edit=sp.edit();
                            edit.putString(Utility.Vendor, new String(responseBody));
                            edit.apply();
                            startActivity(new Intent(VendorRegistration.this,DashBoard.class));

                            finish();
                        }
                        else
                        {
                            Toast.makeText(VendorRegistration.this,"Sorry username already register",Toast.LENGTH_SHORT).show();
                        }
                        pd.dismiss();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        error.printStackTrace();
                        pd.dismiss();
                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

    }

    private boolean valid() {

        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        contact=(EditText)findViewById(R.id.contact);

        if(name.getText().toString().trim().length()==0)
        {
            name.setError("Invalid Name");
            name.requestFocus();

            return false;

        }
        else if(username.getText().toString().trim().length()==0)
        {
            username.setError("Invalid Username");
            username.requestFocus();
            return false;
        }else if(pass.getText().toString().trim().length()==0)
        {
            pass.setError("Invalid Password");
            pass.requestFocus();
            return false;
        }else if(contact.getText().toString().trim().length()==0)
        {
            contact.setError("Invalid Contact");
            contact.requestFocus();
            return false;
        }else if(contact.getText().toString().trim().length()<10 ||contact.getText().toString().trim().length()>10)
        {
            contact.setError("Invalid Contact");
            contact.requestFocus();
            return false;
        }
        else
        {
            return true;
        }

    }
}

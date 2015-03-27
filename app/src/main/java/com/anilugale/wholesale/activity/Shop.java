package com.anilugale.wholesale.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.anilugale.wholesale.R;
import com.anilugale.wholesale.adapter.SpinnerAdapter;
import com.anilugale.wholesale.pojo.Category;
import com.anilugale.wholesale.pojo.Vendor;
import com.anilugale.wholesale.util.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

public class Shop extends ActionBarActivity {
    List<Category> listData;
    Gson gson=new Gson();
    Button save,add;
    SharedPreferences sp;
    EditText name,lat,long1,address;
    Spinner ccategory_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init();
    }

    private void init() {
        sp=getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        ccategory_spinner=(Spinner) findViewById(R.id.category_spinner);
        Type type=new TypeToken<List<Category>>(){}.getType();
        listData =gson.fromJson(sp.getString(Utility.Category,""),type);
        SpinnerAdapter adapter = new SpinnerAdapter(this, listData);
        ccategory_spinner.setAdapter(adapter);
        add=(Button) findViewById(R.id.add_shop);
        save=(Button) findViewById(R.id.save_shop);

        if(sp.getString(Utility.Shop,"").equals(""))
        {
            add.setVisibility(View.VISIBLE);
        }
        name =(EditText)findViewById(R.id.name);
        lat =(EditText)findViewById(R.id.lat);
        long1 =(EditText)findViewById(R.id.long1);
        address =(EditText)findViewById(R.id.address);


    }

    public void checkLatLong(View view) {

        if(lat.getText().length()>0 && long1.getText().length()>0) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat.getText() + "," + long1.getText() + "?q=" + lat.getText() + "," + long1.getText() + "(Shop Location)"));
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Please fill latitude and longitude befor check",Toast.LENGTH_SHORT).show();
        }


    }

    public void add(View view) {

        LinearLayout form=(LinearLayout) findViewById(R.id.form);
        form.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
    }

    public void save(View view) {

        if(validate())
        {
            com.anilugale.wholesale.pojo.Shop shop=new com.anilugale.wholesale.pojo.Shop ();
            shop.setName(name.getText().toString());
            shop.setAddress(address.getText().toString());
            shop.setLat(lat.getText().toString());
            shop.setLag(long1.getText().toString());
            Vendor vendor=gson.fromJson(sp.getString(Utility.Vendor,""),new TypeToken<Vendor>(){}.getType());
            shop.setV_id(vendor.getId());
            shop.setC_id(listData.get(ccategory_spinner.getSelectedItemPosition()).getId());
            Toast.makeText(this,gson.toJson(shop),Toast.LENGTH_SHORT).show();
            System.out.println(gson.toJson(shop));
           final ProgressDialog pd= ProgressDialog.show(this,"","Loading...",true,false);
            try {
                Utility.client.post(this,Utility.ShopURL,new StringEntity(gson.toJson(shop)),"application/json",new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                        Log.e("error",new String(responseBody));
                        pd.dismiss();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        error.printStackTrace();
                        pd.dismiss();
                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }



    }

    private boolean validate() {

        if(name.getText().toString().trim().length()==0)
        {
            name.setError("Invalid Name");
            return false;
        } else if(address.getText().toString().trim().length()==0)
        {
            address.setError("Invalid Address");
            return false;
        }else if(lat.getText().toString().trim().length()==0)
        {
            address.setError("Invalid Address");
            return false;
        }else if(long1.getText().toString().trim().length()==0)
        {
            address.setError("Invalid Address");
            return false;
        }else if(ccategory_spinner.getSelectedItemPosition()==0)
        {
            Toast.makeText(this,"Invalid Category",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}

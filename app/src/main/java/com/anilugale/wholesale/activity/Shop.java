package com.anilugale.wholesale.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anilugale.wholesale.R;
import com.anilugale.wholesale.util.Utility;

public class Shop extends ActionBarActivity {

    Button save,add;
    SharedPreferences sp;
    EditText name,lat,long1,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init();
    }

    private void init() {

        add=(Button) findViewById(R.id.add_shop);
        save=(Button) findViewById(R.id.save_shop);
        sp=getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
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
        view.setVisibility(View.GONE);
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

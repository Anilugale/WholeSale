package com.anilugale.wholesale.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.anilugale.wholesale.R;
import com.anilugale.wholesale.util.Utility;


public class DashBoard extends ActionBarActivity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        sp=getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        if(sp.getString(Utility.Vendor,"").length()==0)
        {
            startActivity(new Intent(this,VendorLogin.class));
            finish();
        }

    }

    public void shop(View view) {
         startActivity(new Intent(this,Shop.class));
    }

    public void advertisement(View view) {
        Toast.makeText(this,"working in progress",Toast.LENGTH_SHORT).show();
    }





   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
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

package com.anilugale.wholesale.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.anilugale.wholesale.R;
import com.anilugale.wholesale.adapter.CategoryAdapter;
import com.anilugale.wholesale.pojo.Category;
import com.anilugale.wholesale.util.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView listView;
    CategoryAdapter adapter;
    SharedPreferences sp;
    Gson gson=new Gson();
    List<Category> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        sp=getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        listView=(ListView) findViewById(R.id.main_list);
        if(sp.getString(Utility.Category,"").equals(""))
        {

           final ProgressDialog pd=ProgressDialog.show(this,"","Loading...",true,false);
            Utility.client.get(Utility.CategoryUrl,new RequestParams(),new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    SharedPreferences.Editor edit=sp.edit();
                    edit.putString(Utility.Category, new String(responseBody));
                    Type type=new TypeToken<List<Category>>(){}.getType();
                    List<Category> listData =gson.fromJson(new String(responseBody),type);
                    adapter = new CategoryAdapter(MainActivity.this, listData);
                    listView.setAdapter(adapter);
                    edit.apply();
                    pd.dismiss();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    pd.dismiss();
                }
            });

        }
        else {
            Type type=new TypeToken<List<Category>>(){}.getType();
           listData =gson.fromJson(sp.getString(Utility.Category,""),type);
            adapter = new CategoryAdapter(this, listData);
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sp.edit().putInt(Utility.Cat_id,listData.get(i).getId()).apply();
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.vendor) {

            startActivity(new Intent(this,DashBoard.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

package com.anilugale.wholesale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.anilugale.wholesale.R;
import com.anilugale.wholesale.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView listView;
    CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        listView=(ListView) findViewById(R.id.main_list);
        List<String> listData=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            listData.add("Category :"+i);
        }
        adapter=new CategoryAdapter(this,listData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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

            startActivity(new Intent(this,VendorLogin.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

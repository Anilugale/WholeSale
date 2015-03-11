package com.anilugale.wholesale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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





}

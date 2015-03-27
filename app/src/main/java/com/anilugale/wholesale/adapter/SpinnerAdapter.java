package com.anilugale.wholesale.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anilugale.wholesale.R;
import com.anilugale.wholesale.pojo.Category;

import java.util.List;

/**
  Created by AnilU on 09-03-2015.
 */
public class SpinnerAdapter extends BaseAdapter {
    Activity activity;
    List<Category> data;
    LayoutInflater inflater;
    public SpinnerAdapter(Activity activity, List<Category> data) {
        this.activity = activity;
        this.data = data;
        Category category=new Category();
        category.setId(-1);
        category.setName("None");
        data.add(0,category);
        inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view==null)
        {
            view =inflater.inflate(R.layout.spinner_list_item,viewGroup,false);
            holder=new ViewHolder();
            holder.name=(TextView)view.findViewById(R.id.name);
            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder) view.getTag();
        }
        holder.name.setText(data.get(i).getName());
        return view;
    }

    static class ViewHolder
    {
        TextView name;
    }
}

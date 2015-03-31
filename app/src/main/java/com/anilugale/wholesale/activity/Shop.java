package com.anilugale.wholesale.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;


public class Shop extends ActionBarActivity {
    List<Category> listData;
    Gson gson = new Gson();
    Button save, add, update;
    SharedPreferences sp;
    EditText name, lat, long1, address, offer;
    Spinner ccategory_spinner;
    com.anilugale.wholesale.pojo.Shop shopupdate;
    Type typeShop = new TypeToken<com.anilugale.wholesale.pojo.Shop>() {
    }.getType();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        sp = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
  //      sp.edit().remove(Utility.Shop).apply();
        if (sp.getString(Utility.Vendor, "").length() == 0) {
            startActivity(new Intent(this, VendorLogin.class));
            finish();
        }

        init();
    }

    private void init() {

        ccategory_spinner = (Spinner) findViewById(R.id.category_spinner);
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        listData = gson.fromJson(sp.getString(Utility.Category, ""), type);
        SpinnerAdapter adapter = new SpinnerAdapter(this, listData);
        ccategory_spinner.setAdapter(adapter);
        add = (Button) findViewById(R.id.add_shop);
        update = (Button) findViewById(R.id.update_shop);
        save = (Button) findViewById(R.id.save_shop);
        name = (EditText) findViewById(R.id.name);
        lat = (EditText) findViewById(R.id.lat);
        long1 = (EditText) findViewById(R.id.long1);
        address = (EditText) findViewById(R.id.address);
        offer = (EditText) findViewById(R.id.offer);

        if (sp.getString(Utility.Shop, "").equals("")) {
            add.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);

        } else {
            add.setVisibility(View.GONE);
            save.setVisibility(View.GONE);

          shopupdate = gson.fromJson(sp.getString(Utility.Shop, ""), typeShop);

            name.setText(shopupdate.getName());
            name.setEnabled(false);

            address.setText(shopupdate.getAddress());
            address.setEnabled(false);

            lat.setText(shopupdate.getLat());
            lat.setEnabled(false);


            long1.setText(shopupdate.getLog());
            long1.setEnabled(false);

            offer.setText(shopupdate.getOffer());
            offer.setEnabled(false);

            int pos = 0;

          /*  for (Category cat : listData) {
                if (cat.getId() == shop.getC_id()) {
                    break;
                }
                pos++;

             }
            ccategory_spinner.setSelection(pos);
            ccategory_spinner.setEnabled(false);*/


        }


    }

    public void checkLatLong(View view) {

        if (lat.getText().length() > 0 && long1.getText().length() > 0) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat.getText() + "," + long1.getText() + "?q=" + lat.getText() + "," + long1.getText() + "(Shop Location)"));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please fill latitude and longitude befor check", Toast.LENGTH_SHORT).show();
        }


    }

    public void add(View view) {

        LinearLayout form = (LinearLayout) findViewById(R.id.form);
        form.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
    }

    public void save(View view) {

        if (validate()) {

            Vendor vendor = gson.fromJson(sp.getString(Utility.Vendor, ""), new TypeToken<Vendor>() {
            }.getType());

           final ProgressDialog pd = ProgressDialog.show(this, "", "Loading...", true, false);
            try {

                JSONObject request=new JSONObject();

                request.put("a_id",0);
                request.put("address",address.getText().toString());
                request.put("c_id",listData.get(ccategory_spinner.getSelectedItemPosition()).getId());
                request.put("lat",lat.getText().toString());
                request.put("log",long1.getText().toString());
                request.put("name",name.getText().toString());
                request.put("v_id",vendor.getId());
                request.put("offer",offer.getText().toString().trim());

                System.out.println(request.toString());
                Utility.client.post(this, Utility.ShopURL, new StringEntity(request.toString()), "application/json", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                        if (statusCode == 200)

                        {

                           String str=new String(responseBody);


                            try {
                                if(new JSONObject(str).getInt("id")!=0)
                                {
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putString(Utility.Shop, new String(responseBody));
                                    edit.apply();
                                    finish();
                                }
                                else {
                                    Toast.makeText(Shop.this, "Shop Already Added", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(Shop.this, "Shop Already Added", Toast.LENGTH_SHORT).show();
                        }
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
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }

    private boolean validate() {

        if (name.getText().toString().trim().length() == 0) {
            name.setError("Invalid Name");
            return false;
        } else if (address.getText().toString().trim().length() == 0) {
            address.setError("Invalid Address");
            return false;
        } else if (lat.getText().toString().trim().length() == 0) {
            address.setError("Invalid Address");
            return false;
        } else if (long1.getText().toString().trim().length() == 0) {
            address.setError("Invalid Address");
            return false;
        } else if (ccategory_spinner.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Invalid Category", Toast.LENGTH_SHORT).show();
            return false;
        } else if (offer.getText().length() == 0) {
            Toast.makeText(this, "Invalid offer", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    public void update(View view) {

       if (validate()) {

           shopupdate.setName(name.getText().toString());
           shopupdate.setAddress(address.getText().toString());
           shopupdate.setLat(lat.getText().toString());
           shopupdate.setLog(long1.getText().toString());
            Vendor vendor = gson.fromJson(sp.getString(Utility.Vendor, ""), new TypeToken<Vendor>() {
            }.getType());
           shopupdate.setV_id(vendor.getId());
           shopupdate.setC_id(listData.get(ccategory_spinner.getSelectedItemPosition()).getId());
           shopupdate.setA_id(0);
           shopupdate.setOffer(offer.getText().toString().trim());

            final ProgressDialog pd = ProgressDialog.show(this, "", "Loading...", true, false);

            try {

                Utility.client.post(this, Utility.ShopUpdateURL, new StringEntity(gson.toJson(shopupdate)), "application/json", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                        if (statusCode == 200)

                        {
                            Log.e("error", new String(responseBody));
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(Utility.Shop, new String(responseBody));
                           edit.apply();
                            Toast.makeText(Shop.this, "updated successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Shop.this, "not update", Toast.LENGTH_SHORT).show();
                        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.edit) {
            update.setVisibility(View.VISIBLE);

            name.setEnabled(true);
            address.setEnabled(true);
            lat.setEnabled(true);
            long1.setEnabled(true);
            offer.setEnabled(true);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

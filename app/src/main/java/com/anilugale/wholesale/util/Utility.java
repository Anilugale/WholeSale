package com.anilugale.wholesale.util;

import com.loopj.android.http.AsyncHttpClient;

/**
* Created by AnilU on 13-03-2015.
 */
public class Utility {
    public static final AsyncHttpClient client = new AsyncHttpClient();
    public static final String Vendor = "Vendor";
    public static final String Shop = "Shop";
    public static final String Category = "Category";
  //  public static final String BasicUrl = "http://shanu.jelastic.elastx.net/wholesale/api/";
    public static final String BasicUrl = "http://192.168.42.40:8080/Wholesaleweb/api/";
    public static final String VendorRegister = BasicUrl+"vendor";
    public static final String  CategoryUrl = BasicUrl+"category";
}




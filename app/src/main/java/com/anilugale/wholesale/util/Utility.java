package com.anilugale.wholesale.util;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by AnilU on 13-03-2015.
 */
public class Utility {
    public  static  final AsyncHttpClient client = new AsyncHttpClient();
    public  static  final String Vendor = "Vendor";
    public  static  final String VendorRegister = "http://192.168.42.40:8080/Wholesaleweb/api/vendor";
}

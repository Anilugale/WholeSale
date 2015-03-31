package com.anilugale.wholesale.pojo;

import java.io.Serializable;

/**
 Created by Anil U on 13-03-2015.
 */
public class Shop implements Serializable {
      int id;
    String name;
    String lat;
    String log;
    int v_id;
    int c_id;
    float distance;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    int a_id;

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    String offer;

    public int getV_id() {
        return v_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;



    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

package com.anilugale.wholesale.pojo;

/**
 Created by Anil U on 13-03-2015.
 */
public class Shop {

    int id;
    String name;
    String offer;
    double lat;
    double lag;

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

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLag() {
        return lag;
    }

    public void setLag(double lag) {
        this.lag = lag;
    }



}

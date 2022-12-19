package com.example.freshapp;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Sales {

    private String cityname;
    private String typename;
    private String weightvalue;
    private String pricevalue;
    private String description;

    public Sales() {
    }

    public Sales(String cityname, String typename, String weightvalue, String pricevalue, String description,String sale_idname) {
        this.cityname = cityname;
        this.typename = typename;
        this.weightvalue = weightvalue;
        this.pricevalue = pricevalue;
        this.description = description;

    }

    public String getCityname() {
        return cityname;
    }

    public String getTypename() {
        return typename;
    }

    public String getWeightvalue() {
        return weightvalue;
    }

    public String getPricevalue() {
        return pricevalue;
    }

    public String getDescription() {
        return description;
    }


}

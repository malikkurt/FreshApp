package com.example.freshapp;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Sales {

    private String city,type,weight,price,description,sale_id,user_id;


    public Sales() {
    }

    public Sales(String city, String type, String weight, String price, String description,String sale_id, String user_id) {
        this.city = city;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.description = description;
        this.sale_id = sale_id;
        this.user_id = user_id;

    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public String getWeight() {
        return weight;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSale_id() {
        return sale_id;
    }

    public String getUser_id() {
        return user_id;
    }
}

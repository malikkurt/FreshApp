package com.example.meetingapp;

public class Meet {

    private String city,time,concept,adress,description,meet_id,user_id;

    public Meet() {
    }

    public Meet(String city, String time, String concept, String adress, String description, String meet_id, String user_id) {

        this.city = city;
        this.time = time;
        this.concept = concept;
        this.adress = adress;
        this.description = description;
        this.meet_id = meet_id;
        this.user_id = user_id;
    }

    public String getCity() {
        return city;
    }

    public String getTime() {
        return time;
    }

    public String getConcept() {
        return concept;
    }

    public String getDescription() {
        return description;
    }

    public String getAdress() {
        return adress;
    }

    public String getMeet_id() {
        return meet_id;
    }

    public String getUser_id() {
        return user_id;
    }
}

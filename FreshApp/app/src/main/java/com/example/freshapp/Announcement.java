package com.example.freshapp;

public class Announcement {

    private String content;
    private String title;

    public Announcement() {
    }

    public Announcement(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public String getContent() {
        return content;
    }


    public String getTitle() {
        return title;
    }


}

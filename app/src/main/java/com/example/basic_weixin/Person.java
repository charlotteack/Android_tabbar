package com.example.basic_weixin;

public class Person {
    private int imgID;
    private String description;

    public Person(int imgID, String description) {
        this.imgID = imgID;
        this.description = description;
    }

    public int getImgID() {
        return this.imgID;
    }

    public String getDescription() {
        return this.description;
    }
}

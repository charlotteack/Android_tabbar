package com.example.basic_weixin;

public class Friend {
    private String name;
    private String sex;
    private int age;

    public Friend(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}

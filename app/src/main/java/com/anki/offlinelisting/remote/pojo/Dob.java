package com.anki.offlinelisting.remote.pojo;

public class Dob {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String date;
    private int age;
}

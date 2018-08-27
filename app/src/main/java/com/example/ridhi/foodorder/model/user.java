package com.example.ridhi.foodorder.model;

/**
 * Created by Ridhi & Sidhi on 1/19/2018.
 */

public class user {

    private String name ;
    private String pass ;
    private String phone ;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public user() {
    }

    public user(String n , String p ){
        name=n;
        pass=p;

    }


}

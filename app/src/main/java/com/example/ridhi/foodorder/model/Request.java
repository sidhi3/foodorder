package com.example.ridhi.foodorder.model;

import java.util.List;

/**
 * Created by Ridhi & Sidhi on 1/28/2018.
 */

public class Request {
    private String phone;
    private String name;
    private String address;
    private String total;
    private List<order> food;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Request() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<order> getFood() {
        return food;
    }

    public void setFood(List<order> food) {
        this.food = food;
    }

    public Request(String s, String toString, List<order> cart) {

    }

    public Request(String phone, String name, String address, String total, List<order> food) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.food = food;
    }
}
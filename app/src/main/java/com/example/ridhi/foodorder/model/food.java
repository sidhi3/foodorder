package com.example.ridhi.foodorder.model;

/**
 * Created by Ridhi & Sidhi on 1/25/2018.
 */

public class food {

    private String name, image, price, menuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public food(String name, String image, String price, String menuid) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.menuid = menuid;
    }
    public food() {
    }
}

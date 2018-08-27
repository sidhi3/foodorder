package com.example.ridhi.foodorder.model;

/**
 * Created by Ridhi & Sidhi on 1/28/2018.
 */

public class order {


    private  String productid;
    private  String productname;
    private  String quantity;
    private  String price;
    private  String discount;


    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public order(String discount) {
        this.discount = discount;
    }

    public order(String productid, String productname, String quantity, String price) {
        this.productid = productid;
        this.productname = productname;

        this.quantity = quantity;
        this.price = price;

    }

    public order(String productid, String productname, String quantity, String price, String discount) {

    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

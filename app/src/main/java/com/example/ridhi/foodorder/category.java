package com.example.ridhi.foodorder;

/**
 * Created by Ridhi & Sidhi on 1/21/2018.
 */

public class category {
    private String name;
    private String image;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public category(String name, String image) {
        this.name = name;
        this.image=image;
    }

    public category() {

    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;

    }
}

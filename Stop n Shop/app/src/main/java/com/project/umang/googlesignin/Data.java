package com.project.umang.googlesignin;

/**
 * Created by UMANG on 6/5/2017.
 */

public class Data {
    public String title;
    public String description;
    public String imageId;
    public int price;
    public int id;

    Data(String title, String description, String imageId, int price,int id) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.price = price;
        this.id = id;
    }
    int getId1()
    {
        return id;
    }
    int getPrice()
    {
        return  price;
    }
    String getTitle1()
    {
        return title;
    }
    String getDescription()
    {
        return description;
    }
    String getImageId()
    {
        return imageId;
    }

}
package com.project.umang.googlesignin;

/**
 * Created by UMANG on 6/11/2017.
 */


/**
 * Created by UMANG on 6/5/2017.
 */

public class DataCart {
    public String title;
    public String description;
    public String imageId;
    public int price;
    public int id;
    public int quantity;


    DataCart(String title, String description, String imageId, int price,int id,int quantity) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.price = price;
        this.id = id;
        this.quantity = quantity;

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
    int getQuantity(){ return  quantity;}

    void setQuantity( int quantity)
    {
        this.quantity = quantity;
    }


}
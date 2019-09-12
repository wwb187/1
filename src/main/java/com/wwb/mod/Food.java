package com.wwb.mod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Food {
    private  int fid;
    private String fname;
    private int flid;
    private double price;
    private String pic;
    private FoodList foodList;

    public Food() {
    }

    public Food(int fid, String fname, int flid, double price, String pic) {
        this.fid = fid;
        this.fname = fname;
        this.flid = flid;
        this.price = price;
        this.pic = pic;
    }

    public Food(int fid) {
        this.fid = fid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getFlid() {
        return flid;
    }

    public void setFlid(int flid) {
        this.flid = flid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public FoodList getFoodList() {
        return foodList;
    }

    public void setFoodList(FoodList foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "Food{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", flid=" + flid +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", foodList=" + foodList +
                '}';
    }
}

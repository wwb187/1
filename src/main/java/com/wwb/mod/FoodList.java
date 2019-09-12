package com.wwb.mod;

public class FoodList {
    private int flid;
    private String flname;

    public FoodList() {
    }

    public FoodList(String flname) {
        this.flname = flname;
    }

    public FoodList(int flid, String flname) {
        this.flid = flid;
        this.flname = flname;
    }

    public int getFlid() {
        return flid;
    }

    public void setFlid(int flid) {
        this.flid = flid;
    }

    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        this.flname = flname;
    }

    @Override
    public String toString() {
        return "FoodList{" +
                "flid=" + flid +
                ", flname='" + flname + '\'' +
                '}';
    }
}

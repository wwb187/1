package com.wwb.mod;


import java.util.Date;

public class Table {
    private int tid;
    private String name;
    private int tp;
    private Date orderTime;

    public Table() {
    }

    public Table(int tid, String name) {
        this.tid = tid;
        this.name = name;
    }

    public Table(int tid, int tp, Date orderTime) {
        this.tid = tid;
        this.tp = tp;
        this.orderTime = orderTime;
    }

    public Table(int tid, String name, int tp, Date orderTime) {
        this.tid = tid;
        this.name = name;
        this.tp = tp;
        this.orderTime = orderTime;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", tp=" + tp +
                ", orderTime=" + orderTime +
                '}';
    }
}

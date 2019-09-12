package com.wwb.mod;

public class Student {
    private  int sid;
    private String sname;
    private int cid;

    public Student() {
    }

    public Student(String sname, int cid) {
        this.sname = sname;
        this.cid = cid;
    }

    public Student(int sid, String sname, int cid) {
        this.sid = sid;
        this.sname = sname;
        this.cid = cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", cid=" + cid +
                '}';
    }
}

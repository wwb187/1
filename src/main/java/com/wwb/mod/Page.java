package com.wwb.mod;

import java.util.HashMap;
import java.util.List;

public class Page<T> {
    // 需要一个limit，需要一个count来表示总的数据量， 需要一个limit来表示每页显示的数目，
    // 需要哦一个pagecount 来表示总页数，pagecount=count/limit 还需要一个data 来存放查询数据的结果 共七个对象
    private  int count;//总数目
    private  int limit;//每页显示的数目
    private  int startIndex;//查询时开始的下标
    private int pageCount;//总页数
    private List<T> data;//查询出来的结果集
    private HashMap condition=null;//条件
    private  int page;

    public Page() {
    }

    public Page(int count, int limit, int startIndex, int pageCount, List<T> data, HashMap condition, int page) {
        this.count = count;
        this.limit = limit;
        this.startIndex = startIndex;
        this.pageCount = pageCount;
        this.data = data;
        this.condition = condition;
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStartIndex() {
        return startIndex=(page-1)*limit;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageCount() {
        return pageCount=count%page==0?count/page:(count/page)+1;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public HashMap getCondition() {
        return condition;
    }

    public void setCondition(HashMap condition) {
        this.condition = condition;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", limit=" + limit +
                ", startIndex=" + getStartIndex() +
                ", pageCount=" + getPageCount() +
                ", data=" + data +
                ", condition=" + condition +
                ", page=" + page +
                '}';
    }
}

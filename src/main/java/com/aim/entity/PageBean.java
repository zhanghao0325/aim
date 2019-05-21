package com.aim.entity;

import java.util.List;

public class PageBean<T> {
    private Integer pageSize;
    private Integer pageNum;
    private Integer tatolCount;
    private Integer tatolPage;
    private List<T> list;

    @Override
    public String toString() {
        return "PageBean{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", tatolCount=" + tatolCount +
                ", tatolPage=" + tatolPage +
                ", list=" + list +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTatolCount() {
        return tatolCount;
    }

    public void setTatolCount(Integer tatolCount) {
        this.tatolCount = tatolCount;
    }

    public Integer getTatolPage() {
        return tatolPage;
    }

    public void setTatolPage(Integer tatolPage) {
        this.tatolPage = tatolPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getPageSize() {

        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

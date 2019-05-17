package com.aim.entity;

import org.apache.solr.client.solrj.beans.Field;

public class UplodFile {
    @Field
    private String id;
    @Field("item_title")
    private String title;
    @Field("item_desc")
    private String dsc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public UplodFile() {
    }
}

package com.example.brusewu.newgan.data.model;

import com.google.gson.annotations.SerializedName;

public class XianDuSite {

    @SerializedName("cat_cn") private String catCN;
    @SerializedName("cat_en") private String catEN;

    private String desc;
    @SerializedName("feed_id") private String feedId;
    private String icon;
    private String id;
    private String name;
    private int subscribers;
    private String type;
    private String url;

    public String getCatCN() {
        return catCN;
    }

    public void setCatCN(String catCN) {
        this.catCN = catCN;
    }

    public String getCatEN() {
        return catEN;
    }

    public void setCatEN(String catEN) {
        this.catEN = catEN;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.example.brusewu.newgan.data.model;

import com.google.gson.annotations.SerializedName;

public class XianDuSubCategory {
    @SerializedName("created_at") private String createdAt;
    @SerializedName("icon") private String iconUrl;
    private String id;
    private String title;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

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
}

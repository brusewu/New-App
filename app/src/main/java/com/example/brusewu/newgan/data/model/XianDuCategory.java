package com.example.brusewu.newgan.data.model;

import com.google.gson.annotations.SerializedName;

public class XianDuCategory {
    @SerializedName("_id") private  String  id;
    @SerializedName("en_name") private  String enName;
    private String name;
    private int rank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

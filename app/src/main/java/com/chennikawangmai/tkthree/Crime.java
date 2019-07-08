package com.chennikawangmai.tkthree;

import com.google.gson.annotations.SerializedName;

public class Crime {
    @SerializedName("body")
    private String persistent_id;
    private String category;
    private long id;

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getPersistent_id() { return persistent_id; }
    public void setPersistent_id(String persistent_id) { this.persistent_id = persistent_id; }
}

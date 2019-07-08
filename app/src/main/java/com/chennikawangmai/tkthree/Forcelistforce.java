package com.chennikawangmai.tkthree;

import com.google.gson.annotations.SerializedName;

public class Forcelistforce {
    private String name;
    @SerializedName("body")
    private String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Forcelistforce() {
    }
}

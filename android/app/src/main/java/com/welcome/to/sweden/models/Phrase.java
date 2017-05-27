package com.welcome.to.sweden.models;

import com.google.gson.annotations.SerializedName;

public class Phrase {

    @SerializedName("swe")
    private String swe;

    @SerializedName("eng")
    private String eng;

    public String getSwe() {
        return swe;
    }

    public String getEng() {
        return eng;
    }
}

package com.welcome.to.sweden.models.currency;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryMap {

    @SerializedName("mappings")
    private List<CountryNameCodeLocale> mList;

    public List<CountryNameCodeLocale> getValues() {
        return mList;
    }

}

package com.welcome.to.sweden.models.sl;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchStation {

    @SerializedName("ResponseData")
    public List<Site> mResponseData;

    public List<Site> getResponseData() {
        if(mResponseData == null) {
            return new ArrayList<>();
        }
        return mResponseData;
    }

}

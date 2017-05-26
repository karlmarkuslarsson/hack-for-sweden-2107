package com.welcome.to.sweden.models.sl;

import com.google.gson.annotations.SerializedName;

class TariffRemark {
    @SerializedName("$")
    private String mCost;

    public String getCost() {
        return mCost;
    }
}

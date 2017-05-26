package com.welcome.to.sweden.models.sl;

import com.google.gson.annotations.SerializedName;

public class SLTrip {

    @SerializedName("TripList")
    private SLTripResponse mResponse;

    public SLTripResponse getResponseList() {
        return mResponse;
    }

}

package com.welcome.to.sweden.models.sl;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class SLTripResponse {

    @SerializedName("Trip")
    private List<Trip> mTrips;

    public List<Trip> getTrips() {
        return mTrips;
    }
}

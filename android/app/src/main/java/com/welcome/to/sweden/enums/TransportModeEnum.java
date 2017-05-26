package com.welcome.to.sweden.enums;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import com.welcome.to.sweden.R;


public enum TransportModeEnum {

    BUS("BUS"),
    METRO("METRO"),
    TRAIN("TRAIN"),
    TRAM("TRAM"),
    SHIP("SHIP");

    @SerializedName("transportMode")
    private final String mTransportMode;

    TransportModeEnum(String transportMode) {
        mTransportMode = transportMode;
    }

    public String getTransportString() {
        return mTransportMode;
    }

    public String getName(Context context) {
        switch (this) {

            case BUS:
                return context.getString(R.string.transportation_bus);
            case METRO:
                return context.getString(R.string.transportation_metro);
            case TRAIN:
                return context.getString(R.string.transportaion_train);
            case TRAM:
                return context.getString(R.string.transportaion_tram);
            case SHIP:
                return context.getString(R.string.transportaion_ship);
        }
        return context.getString(R.string.transportaion_unknown);
    }
}

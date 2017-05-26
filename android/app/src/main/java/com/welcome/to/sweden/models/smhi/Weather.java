package com.welcome.to.sweden.models.smhi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    @SerializedName("timeSeries")
    private List<WeatherPoint> mTimeSeries;

    public List<WeatherPoint> getTimeSeries() {
        if (mTimeSeries == null) {
            return new ArrayList<>();
        }
        return mTimeSeries;
    }

}

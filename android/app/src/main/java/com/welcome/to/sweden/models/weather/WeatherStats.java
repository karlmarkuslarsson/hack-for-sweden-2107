package com.welcome.to.sweden.models.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherStats {

    @SerializedName("stats")
    private List<WeatherStat> stats;

    public List<WeatherStat> getStats() {
        return stats;
    }
}

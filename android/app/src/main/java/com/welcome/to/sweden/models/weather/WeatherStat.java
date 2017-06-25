package com.welcome.to.sweden.models.weather;

import com.google.gson.annotations.SerializedName;

//   {"month_of_year": 12, "name": "December", "high": 1, "low": -3, "water": 4, "sun_hours": 1, "rain_days": 18}
public class WeatherStat {

    @SerializedName("month_of_year")
    private int monthOfYear;

    @SerializedName("name")
    private String name;

    @SerializedName("high")
    private int maxTemperature;

    @SerializedName("low")
    private int minTemperature;

    @SerializedName("water")
    private int waterTemperature;

    @SerializedName("sun_hours")
    private int sunHours;

    @SerializedName("rain_days")
    private int rainyDays;

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getTemperature() {
        return Math.round(((float) minTemperature + maxTemperature)/2f);
    }

}

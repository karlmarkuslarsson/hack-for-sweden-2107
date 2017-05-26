package com.welcome.to.sweden.enums;

import com.google.gson.annotations.SerializedName;

public enum WeatherParamName {

    @SerializedName("msl")
    AIR_PRESSURE("msl"), //hPa
    @SerializedName("t")
    TEMPERATURE("t"),   // C
    @SerializedName("vis")
    VISIBILITY("vis"), // km
    @SerializedName("wd")
    WIND_DIRECTION("wd"), //degree
    @SerializedName("ws")
    WIND_SPEED("ws"), // m/
    @SerializedName("r")
    RELATIVE_HUMIDITY("r"), // %
    @SerializedName("tstm")
    THUNDER_PROB("tstm"), // %
    @SerializedName("tcc_mean")
    MEAN_VALUE_TOTAL_CLOUD_VALUE("tcc_mean"), // octas 0-8
    @SerializedName("lcc_mean")
    MEAN_VALUE_LOW_CLOUD_VALUE("lcc_mean"), // octas 0-8
    @SerializedName("mcc_mean")
    MEAN_VALUE_MEDIUM_CLOUD_VALUE("mcc_mean"), // octas 0-8
    @SerializedName("hcc_mean")
    MEAN_VALUE_HIGH_CLOUD_VALUE("hcc_mean"), // octas 0-8
    @SerializedName("gust")
    WIND_GUST_SPEED("gust"), // m/s
    @SerializedName("pmin")
    MINIMUM_PERCIPITATION("pmin"), // mm/h
    @SerializedName("pmax")
    MAXIMUM_PERCIPITATION("pmax"), // mm/h
    @SerializedName("spp")
    PERCENT_OF_PERCIPITATION_FROZEN("spp"), // % -6, 0-100
    @SerializedName("pcat")
    PERCIPITATION_CATEGORY("pcat"), // category 0-6
    @SerializedName("pmean")
    MEAN_PERCIPITATION_ITENSITY("pmean"), // mm/h
    @SerializedName("pmedian")
    MEDIAN_PERCIPITATION_ITENSITY("pmedian"), // mm/h
    @SerializedName("Wsymb")
    WEATHER_SYMBOL("Wsymb"); // code 1-15

    @SerializedName("name")
    private final String mName;

    WeatherParamName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

}
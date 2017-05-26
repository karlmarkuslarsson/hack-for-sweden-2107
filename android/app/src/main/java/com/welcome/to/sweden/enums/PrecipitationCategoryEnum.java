package com.welcome.to.sweden.enums;

import com.google.gson.annotations.SerializedName;

public enum PrecipitationCategoryEnum {
    NO_PRECIPITATION(0),
    SNOW(1),
    SNOW_RAIN(2),
    RAIN(3),
    DRIZZLE(4),
    FREEZING_RAIN(5),
    FREEZING_DRIZZLE(6);

    @SerializedName("value")
    private final int mValue;

    PrecipitationCategoryEnum(int value) {
        mValue = value;
    }

}

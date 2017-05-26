package com.welcome.to.sweden.models.cards.holdays;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.welcome.to.sweden.models.cards.CardComponent;

public class Holidays extends CardComponent {

    public static final String TYPE = "holidays";

    @SerializedName("holidays")
    private List<Holiday> mHolidays;

    public List<Holiday> getHolidays() {
        return mHolidays;
    }
}

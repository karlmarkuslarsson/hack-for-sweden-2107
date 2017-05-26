package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.holdays.Holidays;
import com.welcome.to.sweden.objects.main.base.Card;

public class HolidaysCard extends Card {
    private final Holidays mHolidays;

    public HolidaysCard(Holidays content) {
        mHolidays = content;
    }

    public Holidays getHolidays() {
        return mHolidays;
    }
}

package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.holdays.Holidays;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class HolidaysCard extends MainCard {
    private final Holidays mHolidays;

    public HolidaysCard(Holidays content) {
        mHolidays = content;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_HOLIDAYS;
    }

    public Holidays getHolidays() {
        return mHolidays;
    }
}

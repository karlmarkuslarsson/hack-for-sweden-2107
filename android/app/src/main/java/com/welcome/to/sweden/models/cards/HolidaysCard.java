package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.Holiday;
import com.welcome.to.sweden.models.cards.base.CardComponent;

import java.util.List;

public class HolidaysCard extends CardComponent {

    private List<Holiday> list;

    public HolidaysCard(List<Holiday> list) {
        this.list = list;
    }

    public List<Holiday> getHolidays() {
        return list;
    }
}

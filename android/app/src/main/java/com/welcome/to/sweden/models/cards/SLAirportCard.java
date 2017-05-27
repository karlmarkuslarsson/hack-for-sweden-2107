package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.sl.SLTrip;

public class SLAirportCard extends Card {
    private final SLTrip mTrip;

    public SLAirportCard(SLTrip trip) {
        mTrip = trip;
    }

    public SLTrip getTrip() {
        return mTrip;
    }
}

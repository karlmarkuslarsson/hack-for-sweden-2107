package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.sl.SLTrip;
import com.welcome.to.sweden.objects.main.base.Card;

public class SLAirportCard extends Card {
    private final SLTrip mTrip;

    public SLAirportCard(SLTrip trip) {
        mTrip = trip;
    }

    public SLTrip getTrip() {
        return mTrip;
    }
}

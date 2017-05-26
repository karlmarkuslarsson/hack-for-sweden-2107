package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.sl.SLTrip;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class SLAirportCard extends MainCard {
    private final SLTrip mTrip;

    public SLAirportCard(SLTrip trip) {
        mTrip = trip;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_SL_AIRPORT_TRIP;
    }

    public SLTrip getTrip() {
        return mTrip;
    }
}

package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.objects.main.base.MainCard;

public class TripTransportationCard extends MainCard {

    private final String mTransportTime;

    public TripTransportationCard(String transportTime) {
        mTransportTime = transportTime;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_TRANSPORTATION;
    }

    public String getTransportTime() {
        return mTransportTime;
    }
}

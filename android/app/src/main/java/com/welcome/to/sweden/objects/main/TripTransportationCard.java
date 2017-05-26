package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.objects.main.base.Card;

public class TripTransportationCard extends Card {

    private final String mTransportTime;

    public TripTransportationCard(String transportTime) {
        mTransportTime = transportTime;
    }

    public String getTransportTime() {
        return mTransportTime;
    }
}

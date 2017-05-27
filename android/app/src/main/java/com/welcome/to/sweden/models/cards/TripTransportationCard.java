package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.Card;

public class TripTransportationCard extends Card {

    private final String mTransportTime;

    public TripTransportationCard(String transportTime) {
        mTransportTime = transportTime;
    }

    public String getTransportTime() {
        return mTransportTime;
    }
}

package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.MyTripEvent;

public class TripPlaceCard extends Card {

    private final MyTripEvent mTripEvent;
    private String mStartTime;

    public TripPlaceCard(MyTripEvent tripRestaurant, String startTime) {
        mStartTime = startTime;
        mTripEvent = tripRestaurant;
    }

    public MyTripEvent getTripEvent() {
        return mTripEvent;
    }

    public String getStartTime() {
        return mStartTime;
    }
}

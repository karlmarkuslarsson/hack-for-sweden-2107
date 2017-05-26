package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.myTrip.MyTripEvent;
import com.welcome.to.sweden.objects.main.base.Card;

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

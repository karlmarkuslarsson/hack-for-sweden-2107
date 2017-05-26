package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.myTrip.MyTripEvent;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class TripPlaceCard extends MainCard {

    private final MyTripEvent mTripEvent;
    private String mStartTime;

    public TripPlaceCard(MyTripEvent tripRestaurant, String startTime) {
        mStartTime = startTime;
        mTripEvent = tripRestaurant;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_PLACE;
    }

    public MyTripEvent getTripEvent() {
        return mTripEvent;
    }

    public String getStartTime() {
        return mStartTime;
    }
}

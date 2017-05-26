package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.myTrip.MyTripRestaurant;
import com.welcome.to.sweden.objects.main.base.Card;


public class TripDinnerCard extends Card {

    private final MyTripRestaurant mTripRestaurant;
    private final int mDuration;
    private String mStartTime;

    public TripDinnerCard(MyTripRestaurant tripEvent, String startTime, int duration) {
        mTripRestaurant = tripEvent;
        mStartTime = startTime;
        mDuration = duration;
    }

    public MyTripRestaurant getTripRestaurant() {
        return mTripRestaurant;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public int getDuration() {
        return mDuration;
    }
}

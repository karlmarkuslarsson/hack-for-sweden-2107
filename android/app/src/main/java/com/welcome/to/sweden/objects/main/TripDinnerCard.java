package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.myTrip.MyTripRestaurant;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class TripDinnerCard extends MainCard {

    private final MyTripRestaurant mTripRestaurant;
    private final int mDuration;
    private String mStartTime;

    public TripDinnerCard(MyTripRestaurant tripEvent, String startTime, int duration) {
        mTripRestaurant = tripEvent;
        mStartTime = startTime;
        mDuration = duration;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_FOOD;
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

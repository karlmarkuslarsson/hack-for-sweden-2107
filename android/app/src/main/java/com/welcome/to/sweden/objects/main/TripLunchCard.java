package com.welcome.to.sweden.objects.main;


import com.welcome.to.sweden.models.cards.myTrip.MyTripRestaurant;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class TripLunchCard extends MainCard {
    private final int mDuration;
    private String mStartTime;

    public TripLunchCard(String startTime, int duration) {
        mStartTime = startTime;
        mDuration = duration;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_LUNCH;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public int getDuration() {
        return mDuration;
    }
}

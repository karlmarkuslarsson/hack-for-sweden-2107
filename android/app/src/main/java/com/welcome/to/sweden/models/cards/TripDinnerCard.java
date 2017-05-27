package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.R;
import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.MyTripRestaurant;


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

    public int getType() {
        return R.string.card_type_dinner;
    }
}

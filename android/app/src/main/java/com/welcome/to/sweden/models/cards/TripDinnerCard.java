package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.R;
import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.TripRestaurant;


public class TripDinnerCard extends Card {

    private final TripRestaurant mTripRestaurant;
    private final int mDuration;
    private String mStartTime;

    public TripDinnerCard(TripRestaurant tripEvent, String startTime, int duration) {
        mTripRestaurant = tripEvent;
        mStartTime = startTime;
        mDuration = duration;
    }

    public TripRestaurant getTripRestaurant() {
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

package com.welcome.to.sweden.models.cards;


import com.welcome.to.sweden.models.cards.base.Card;

public class TripLunchCard extends Card {
    private final int mDuration;
    private String mStartTime;

    public TripLunchCard(String startTime, int duration) {
        mStartTime = startTime;
        mDuration = duration;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public int getDuration() {
        return mDuration;
    }
}

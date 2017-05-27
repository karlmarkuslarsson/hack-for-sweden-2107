package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.Card;

public class NextDayDivider extends Card {
    private final int mCounter;

    public NextDayDivider(int counter) {
        mCounter = counter;
    }

    public int getCounter() {
        return mCounter;
    }
}

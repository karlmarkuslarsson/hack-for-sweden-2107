package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.objects.main.base.Card;

public class NextDayDivider extends Card {
    private final int mCounter;

    public NextDayDivider(int counter) {
        mCounter = counter;
    }

    public int getCounter() {
        return mCounter;
    }
}

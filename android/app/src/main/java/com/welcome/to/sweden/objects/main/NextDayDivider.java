package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.objects.main.base.MainCard;

public class NextDayDivider extends MainCard {
    private final int mCounter;

    public NextDayDivider(int counter) {
        mCounter = counter;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_NEXT_DAY_DIVIDER;
    }

    public int getCounter() {
        return mCounter;
    }
}

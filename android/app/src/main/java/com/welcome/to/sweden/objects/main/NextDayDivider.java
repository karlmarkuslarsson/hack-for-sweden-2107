package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.objects.main.base.MainCard;

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

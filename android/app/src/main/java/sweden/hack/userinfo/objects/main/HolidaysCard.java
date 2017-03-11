package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.holdays.Holidays;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class HolidaysCard extends MainCard {
    private final Holidays mHolidays;

    public HolidaysCard(Holidays content) {
        mHolidays = content;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_HOLIDAYS;
    }

    public Holidays getHolidays() {
        return mHolidays;
    }
}

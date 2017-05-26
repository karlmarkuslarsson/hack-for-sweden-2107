package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.sl.SLTrip;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class SLAirportCard extends MainCard {
    private final SLTrip mTrip;

    public SLAirportCard(SLTrip trip) {
        mTrip = trip;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_SL_AIRPORT_TRIP;
    }

    public SLTrip getTrip() {
        return mTrip;
    }
}

package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripPlaceCard extends MainCard {
    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_PLACE;
    }
}

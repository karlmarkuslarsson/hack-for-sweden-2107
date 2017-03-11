package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.myTrip.MyTripEvent;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripPlaceCard extends MainCard {

    private final MyTripEvent mTripEvent;

    public TripPlaceCard (MyTripEvent tripRestaurant) {
        mTripEvent = tripRestaurant;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_PLACE;
    }

    public MyTripEvent getTripEvent() {
        return mTripEvent;
    }
}

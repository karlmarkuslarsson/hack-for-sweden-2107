package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.myTrip.MyTripEvent;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripFoodCard extends MainCard {

    private final MyTripEvent mTripEvent;

    public TripFoodCard (MyTripEvent tripEvent) {
        mTripEvent = tripEvent;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_FOOD;
    }

    public MyTripEvent getTripEvent() {
        return mTripEvent;
    }
}

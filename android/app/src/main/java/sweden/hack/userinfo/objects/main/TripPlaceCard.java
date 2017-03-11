package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.myTrip.MyTripRestaurant;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripPlaceCard extends MainCard {

    private final MyTripRestaurant mTripRestaurant;

    public TripPlaceCard (MyTripRestaurant tripRestaurant) {
        mTripRestaurant = tripRestaurant;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_PLACE;
    }

    public MyTripRestaurant getTripRestaurant() {
        return mTripRestaurant;
    }
}

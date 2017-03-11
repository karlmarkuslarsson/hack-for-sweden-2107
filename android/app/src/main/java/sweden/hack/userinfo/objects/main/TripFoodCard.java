package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.myTrip.MyTripRestaurant;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripFoodCard extends MainCard {

    private final MyTripRestaurant mTripRestaurant;

    public TripFoodCard (MyTripRestaurant tripEvent) {
        mTripRestaurant = tripEvent;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_FOOD;
    }

    public MyTripRestaurant getTripRestaurant() {
        return mTripRestaurant;
    }
}

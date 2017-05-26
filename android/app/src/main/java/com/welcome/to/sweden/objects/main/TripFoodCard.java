package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.cards.myTrip.MyTripRestaurant;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripFoodCard extends MainCard {

    private final MyTripRestaurant mTripRestaurant;
    private final int mDuration;
    private String mStartTime;

    public TripFoodCard(MyTripRestaurant tripEvent, String startTime, int duration) {
        mTripRestaurant = tripEvent;
        mStartTime = startTime;
        mDuration = duration;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_TRIP_FOOD;
    }

    public MyTripRestaurant getTripRestaurant() {
        return mTripRestaurant;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public int getDuration() {
        return mDuration;
    }
}

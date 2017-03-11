package sweden.hack.userinfo.models.myTrip;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sweden.hack.userinfo.models.CardComponent;

public class MyTrip extends CardComponent {

    @SerializedName("events")
    private List<MyTripEvent> mEvents;

    public List<MyTripEvent> getEvents() {
        return mEvents;
    }

    @SerializedName("restaurants")
    private List<MyTripRestaurant> mRestaurants;

    public List<MyTripRestaurant> getRestaurants() {
        return mRestaurants;
    }


    public MyTripRestaurant getRestaurant(String id) {
        return null;
    }

    public MyTripEvent getEvent(String id) {
        return null;
    }
}


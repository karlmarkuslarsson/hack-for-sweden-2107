package com.welcome.to.sweden.models.cards;

import com.google.gson.annotations.SerializedName;
import com.welcome.to.sweden.models.MyTripEvent;
import com.welcome.to.sweden.models.MyTripRestaurant;

import java.util.List;

public class MyTrip  {

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
        for(MyTripRestaurant restaurant: mRestaurants) {
            if(restaurant.getId().equals(id)) {
                return restaurant;
            }
        }
        return null;
    }

    public MyTripEvent getEvent(String id) {
        for(MyTripEvent event: mEvents) {
            if(event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

}


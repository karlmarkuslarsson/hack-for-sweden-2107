package com.welcome.to.sweden.models.cards;

import com.google.gson.annotations.SerializedName;
import com.welcome.to.sweden.models.TripEvent;
import com.welcome.to.sweden.models.TripRestaurant;

import java.util.List;

public class TripData {

    @SerializedName("events")
    private List<TripEvent> mEvents;

    @SerializedName("restaurants")
    private List<TripRestaurant> mRestaurants;

    public List<TripEvent> getEvents() {
        return mEvents;
    }

    public List<TripRestaurant> getRestaurants() {
        return mRestaurants;
    }

    public TripRestaurant getRestaurant(String id) {
        for(TripRestaurant restaurant: mRestaurants) {
            if(restaurant.getId().equals(id)) {
                return restaurant;
            }
        }
        return null;
    }

    public TripEvent getEvent(String id) {
        for(TripEvent event: mEvents) {
            if(event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

}


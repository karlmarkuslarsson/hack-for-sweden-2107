package com.welcome.to.sweden.objects;

import java.io.Serializable;
import java.util.ArrayList;

import com.welcome.to.sweden.enums.TripObjectType;
import com.welcome.to.sweden.models.cards.myTrip.MyTripEvent;
import com.welcome.to.sweden.models.cards.myTrip.MyTripRestaurant;

public class TripPath implements Serializable {
    private ArrayList<TripObject> objectList;

    public TripPath() {
        objectList = new ArrayList<>();
    }

    public ArrayList<TripObject> getObjectList() {
        return objectList;
    }

    public void add(MyTripEvent tripEvent) {
        objectList.add(new TripObject(TripObjectType.EVENT, tripEvent.getId()));
    }

    public void add(MyTripRestaurant tripRestaurant) {
        objectList.add(new TripObject(TripObjectType.RESTAURANT, tripRestaurant.getId()));
    }

    public void addTransfer() {
        objectList.add(new TripObject(TripObjectType.TRANSFER));
    }

}

package com.welcome.to.sweden.objects;

import com.welcome.to.sweden.enums.TripObjectType;
import com.welcome.to.sweden.models.TripEvent;
import com.welcome.to.sweden.models.TripRestaurant;

import java.io.Serializable;
import java.util.ArrayList;

public class TripPath implements Serializable {
    private ArrayList<TripObject> objectList;

    public TripPath() {
        objectList = new ArrayList<>();
    }

    public ArrayList<TripObject> getObjectList() {
        return objectList;
    }

    public ArrayList<TripObject> getSelectedObjects(TripObjectType filteredType) {
        ArrayList<TripObject> selectedObjects = new ArrayList<>();

        for (TripObject tripObject : objectList) {
            if(tripObject.getTripObjectType() == filteredType) {
                selectedObjects.add(tripObject);
            }
        }
        return selectedObjects;
    }

    public void add(TripEvent tripEvent) {
        objectList.add(new TripObject(TripObjectType.EVENT, tripEvent.getId()));
    }

    public void add(TripRestaurant tripRestaurant) {
        objectList.add(new TripObject(TripObjectType.RESTAURANT, tripRestaurant.getId()));
    }

    public void addTransfer() {
        objectList.add(new TripObject(TripObjectType.TRANSFER));
    }

    public void addLunchStop() {
        objectList.add(new TripObject(TripObjectType.LUNCH));
    }
}

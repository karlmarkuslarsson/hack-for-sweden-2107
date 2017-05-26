package com.welcome.to.sweden.objects;

import java.io.Serializable;

import com.welcome.to.sweden.enums.TripObjectType;

public class TripObject implements Serializable {

    private TripObjectType tripObjectType;

    private String id;

    public TripObject(TripObjectType transfer) {
        tripObjectType = transfer;
    }

    public TripObject(TripObjectType transfer, String id) {
        tripObjectType = transfer;
        this.id = id;
    }

    public TripObjectType getTripObjectType() {
        return tripObjectType;
    }

    public String getId() {
        return id;
    }
}

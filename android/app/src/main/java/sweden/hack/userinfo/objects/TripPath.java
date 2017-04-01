package sweden.hack.userinfo.objects;

import java.io.Serializable;
import java.util.ArrayList;

import sweden.hack.userinfo.enums.TripObjectType;
import sweden.hack.userinfo.models.cards.myTrip.MyTripEvent;
import sweden.hack.userinfo.models.cards.myTrip.MyTripRestaurant;

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

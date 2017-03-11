package sweden.hack.userinfo.helpers;

import java.util.ArrayList;

import sweden.hack.userinfo.models.myTrip.MyTrip;
import sweden.hack.userinfo.objects.TripPath;

public class TripCalculator {
    public static ArrayList<TripPath> calculateTrips(MyTrip tripData, int days) {
        ArrayList<TripPath> trips = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            TripPath tripPath = new TripPath();
            for(int j = 0; j < 3; j++) {
                tripPath.add(tripData.getEvents().get(j));
            }
            tripPath.addTransfer();
            for(int j = 0; j < 3; j++) {
                tripPath.add(tripData.getRestaurants().get(j));
            }

        }
        return trips;
    }
}

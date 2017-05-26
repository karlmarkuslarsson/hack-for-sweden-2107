package com.welcome.to.sweden.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.welcome.to.sweden.enums.TripObjectType;
import com.welcome.to.sweden.models.cards.myTrip.MyTrip;
import com.welcome.to.sweden.models.cards.myTrip.MyTripEvent;
import com.welcome.to.sweden.models.cards.myTrip.MyTripRestaurant;
import com.welcome.to.sweden.objects.TripPath;

public class TripCalculator {

    private static List<TripPart> sTemplate = getDefaultTemplate();

    private static List<TripPart> getDefaultTemplate() {
        List<TripPart> template = new ArrayList<>();
        template.add(new TripPart(TripObjectType.EVENT, 120));
        template.add(new TripPart(TripObjectType.RESTAURANT, 60));
        template.add(new TripPart(TripObjectType.EVENT, 240));
        // add paus
        template.add(new TripPart(TripObjectType.RESTAURANT, 120));
        template.add(new TripPart(TripObjectType.EVENT, 180));
        return template;
    }

    public static ArrayList<TripPath> calculateTrips(MyTrip tripData, int days) {
        List<MyTripEvent> events = new ArrayList<>(tripData.getEvents());
        Collections.shuffle(events); // change to more smart solution

        List<MyTripRestaurant> restaurants = new ArrayList<>(tripData.getRestaurants());
        Collections.shuffle(restaurants); // change to more smart solution


        ArrayList<TripPath> trips = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            TripPath tripPath = new TripPath();

            int totalTime = 0;
            for (TripPart tripPart : sTemplate) {
                int timeLeft = tripPart.duration;
                while (hasTimeLeft(timeLeft, tripPath)) {
                    switch (tripPart.eventType) {
                        case RESTAURANT:
                            tripPath.add(restaurants.get(0));
                            restaurants.remove(0);
                            timeLeft = 0;
                            tripPath.addTransfer();
                            break;
                        case EVENT:
                            int timeTaken = addNextEvent(timeLeft, events, tripPath);
                            if (timeTaken == 0) {
                                timeLeft = 0;
                            } else {
                                tripPath.addTransfer();
                            }
                            totalTime += timeTaken;
                            timeLeft -= timeTaken;
                            break;
                    }
                }
            }
            trips.add(tripPath);
        }
        return trips;
    }

    private static int addNextEvent(int timeLeft, List<MyTripEvent> events, TripPath tripPath) {
        int id = 0;
        int dur = 0;
        for (MyTripEvent event : events) {
            if (event.getDuration() <= timeLeft) {
                tripPath.add(event);
                dur = event.getDuration();
                break;
            }
            id++;
        }
        if (dur != 0) {
            events.remove(id);
        }
        return dur;
    }

    private static boolean hasTimeLeft(int timeLeft, TripPath tripPath) {
        if (timeLeft <= 15) {
            return false;
        }
        return true;
    }

    private static class TripPart {

        private final TripObjectType eventType;
        private final int duration;

        public TripPart(TripObjectType eventType, int duration) {
            this.eventType = eventType;
            this.duration = duration;
        }

    }
}

package com.welcome.to.sweden.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.welcome.to.sweden.enums.TripObjectType;
import com.welcome.to.sweden.models.cards.MyTrip;
import com.welcome.to.sweden.models.MyTripEvent;
import com.welcome.to.sweden.models.MyTripRestaurant;
import com.welcome.to.sweden.objects.TripPath;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TripCalculator {

    private static final int MINIMUM_EVENT_TIME = 15;
    private static List<TripPart> sTemplate = new ArrayList<TripPart>() {{
        add(new TripPart(TripObjectType.EVENT, 100));
        add(new TripPart(TripObjectType.LUNCH, 45));
        add(new TripPart(TripObjectType.EVENT, 200));
        // add pause
        add(new TripPart(TripObjectType.RESTAURANT, 120));
        add(new TripPart(TripObjectType.EVENT, 120));
    }};

    public static List<TripPart> getDefaultTemplate() {
        return sTemplate;
    }

    public static ArrayList<TripPath> calculateTrips(
            MyTrip tripData,
            int days,
            List<TripPart> template) {
        List<MyTripEvent> shuffledEvents = shuffleAndCopyList(tripData.getEvents());
        List<MyTripRestaurant> shuffledRestaurants = shuffleAndCopyList(tripData.getRestaurants());
        return calculateTrips(shuffledEvents, shuffledRestaurants, days, template);
    }

    public static ArrayList<TripPath> calculateTrips(
            List<MyTripEvent> shuffledEvents,
            List<MyTripRestaurant> shuffledRestaurants,
            int days,
            List<TripPart> template) {

        ArrayList<TripPath> trips = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            TripPath tripPath = new TripPath();
            for (TripPart tripPart : template) {
                int timeLeft = tripPart.duration;
                while (timeLeft >= MINIMUM_EVENT_TIME) {
                    switch (tripPart.eventType) {
                        case RESTAURANT:
                            if (!shuffledRestaurants.isEmpty()) {
                                tripPath.add(shuffledRestaurants.get(0));
                                shuffledRestaurants.remove(0);
                                tripPath.addTransfer();
                            }
                            timeLeft = 0;
                            break;
                        case LUNCH:
                            tripPath.addLunchStop();
                            timeLeft = 0;
                            tripPath.addTransfer();
                            break;
                        case EVENT:
                            int timeTaken = addNextEvent(timeLeft, shuffledEvents, tripPath);
                            if (timeTaken == 0) {
                                timeLeft = 0;
                            } else {
                                tripPath.addTransfer();
                            }
                            timeLeft -= timeTaken;
                            break;
                    }
                }
            }
            trips.add(tripPath);
        }
        return trips;
    }

    protected static <T> List<T> shuffleAndCopyList(@Nullable List<T> events) {
        if (events == null) {
            return null;
        }
        List<T> shuffledEvents = new ArrayList<>(events);
        Collections.shuffle(shuffledEvents);
        return shuffledEvents;
    }

    /**
     * @return 0 if there isn´t any event.
     */
    protected static int addNextEvent(
            int timeLeft,
            @Nonnull List<MyTripEvent> events,
            @Nonnull TripPath tripPath) {

        Iterator<MyTripEvent> iterator = events.iterator();
        while (iterator.hasNext()) {
            MyTripEvent event = iterator.next();
            if (event.getDuration() <= timeLeft) {
                tripPath.add(event);
                iterator.remove();
                return event.getDuration();
            }
        }
        return 0;
    }

    protected static class TripPart {

        private final TripObjectType eventType;
        private final int duration;

        public TripPart(TripObjectType eventType, int duration) {
            this.eventType = eventType;
            this.duration = duration;
        }
    }

}

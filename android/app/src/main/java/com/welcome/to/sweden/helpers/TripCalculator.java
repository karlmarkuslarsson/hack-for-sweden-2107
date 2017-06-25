package com.welcome.to.sweden.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.welcome.to.sweden.enums.TripObjectType;
import com.welcome.to.sweden.models.TripLocation;
import com.welcome.to.sweden.models.cards.TripData;
import com.welcome.to.sweden.models.TripEvent;
import com.welcome.to.sweden.models.TripRestaurant;
import com.welcome.to.sweden.objects.TripPath;

import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import timber.log.Timber;

public class TripCalculator {

    public static final int LUNCH_TIME = 45; //minutes
    public static final int RESTAURANT_TIME = 120; //minutes
    private static final int MINIMUM_EVENT_TIME = 15; //minutes

    private static List<TripPart> sTemplate = new ArrayList<TripPart>() {{
        add(new TripPart(TripObjectType.EVENT, 100));
        add(new TripPart(TripObjectType.LUNCH, LUNCH_TIME));
        add(new TripPart(TripObjectType.EVENT, 300));
        // add pause
        add(new TripPart(TripObjectType.RESTAURANT, RESTAURANT_TIME));
        add(new TripPart(TripObjectType.EVENT, 90));
    }};

    public static List<TripPart> getDefaultTemplate() {
        return sTemplate;
    }

    public static ArrayList<TripPath> calculateTrips(
            TripData tripData,
            int days,
            List<TripPart> template,
            DateTime startDate,
            int temperature) {
        List<TripEvent> shuffledEvents = shuffleAndCopyList(tripData.getEvents());
        List<TripRestaurant> shuffledRestaurants = shuffleAndCopyList(tripData.getRestaurants());
        return calculateTrips(shuffledEvents, shuffledRestaurants, days, template, startDate, temperature);
    }

    public static ArrayList<TripPath> calculateTrips(
            List<TripEvent> shuffledEvents,
            List<TripRestaurant> shuffledRestaurants,
            int days,
            List<TripPart> template,
            DateTime startDate,
            int temperature) {

        ArrayList<TripPath> trips = new ArrayList<>();
        for (int day = 0; day < days; day++) {
            DateTime date = getStartOfDay(startDate, day);
            TripLocation previousEvent = null;
            TripPath tripPath = new TripPath();
            for (TripPart tripPart : template) {
                int timeLeft = tripPart.duration;
                while (timeLeft >= MINIMUM_EVENT_TIME) {
                    switch (tripPart.eventType) {
                        case RESTAURANT:
                            if (!shuffledRestaurants.isEmpty()) {
                                TripRestaurant restaurantEvent = shuffledRestaurants.get(0);
                                tripPath.add(restaurantEvent);

                                // transfer time
                                date = date.plusMinutes(
                                        getTransferTimeInMinutes(previousEvent, restaurantEvent));

                                previousEvent = restaurantEvent;
                                shuffledRestaurants.remove(0);
                                tripPath.addTransfer();
                                date = date.plusMinutes(RESTAURANT_TIME);
                            }
                            timeLeft = 0;
                            break;
                        case LUNCH:
                            previousEvent = null;
                            tripPath.addLunchStop();
                            timeLeft = 0;
                            tripPath.addTransfer();
                            date = date.plusMinutes(LUNCH_TIME);
                            break;
                        case EVENT:
                            TripEvent event = addNextEvent
                                    (timeLeft, shuffledEvents, tripPath, previousEvent, date, temperature);
                            int timeTaken = event != null ? event.getDuration() : 0;
                            if (timeTaken == 0) {
                                timeLeft = 0;
                            } else {
                                // transfer time
                                date = date.plusMinutes(
                                        getTransferTimeInMinutes(previousEvent, event));

                                tripPath.addTransfer();
                                previousEvent = event;
                            }
                            date = date.plusMinutes(timeTaken);
                            timeLeft -= timeTaken;
                            break;
                    }
                }
            }
            trips.add(tripPath);
        }
        return trips;
    }

    private static int getTransferTimeInMinutes(
            @Nullable TripLocation fromEvent, TripLocation toEvent) {

        if (fromEvent == null) {
            return 0;
        }
        return LocationHelper.getTimeBetweenEvents(fromEvent, toEvent);
    }

    private static DateTime getStartOfDay(DateTime startDate, int day) {
        return new DateTime(startDate)
                .withHourOfDay(10)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .plusDays(day);
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
    protected static TripEvent addNextEvent(
            int timeLeft,
            @Nonnull List<TripEvent> events,
            @Nonnull TripPath tripPath,
            @Nullable TripLocation previousEvent,
            @Nonnull DateTime currentTimeBeforeTransfer,
            int temperature) {

        Iterator<TripEvent> iterator = events.iterator();
        while (iterator.hasNext()) {
            TripEvent event = iterator.next();
            if (withinTime(event, timeLeft)
                    && closeEnough(event, previousEvent)
                    && isOpen(currentTimeBeforeTransfer, previousEvent, event)
                    && correctTemperature(event, temperature)) {
                tripPath.add(event);
                iterator.remove();
                return event;
            }
        }
        return null;
    }

    private static boolean correctTemperature(TripEvent event, int temperature) {
        // too cold outside
        if (event.getMinTemp() != null && event.getMinTemp() > temperature) {
            Timber.d("Filter out %s [too cold outside]", event);
            return false;
        }
        // too hot outside
        if (event.getMaxTemp() != null && event.getMaxTemp() < temperature) {
            Timber.d("Filter out %s [too hot outside]", event);
            return false;
        }
        return true;
    }

    private static boolean isOpen(
            DateTime currentTimeBeforeTransfer,
            TripLocation previousEvent,
            TripEvent event) {
        int transferTime = getTransferTimeInMinutes(previousEvent, event);
        DateTime startTime = currentTimeBeforeTransfer.plusMinutes(transferTime);
        DateTime endTime = startTime.plusMinutes(event.getDuration());

        // check if time is before first opened month
        if (event.getFirstMonth() != null && event.getFirstMonth() > startTime.getMonthOfYear()) {
            Timber.d("Filter out %s [before first month]", event);
            return false;
        }

        // check if time is after last opened month
        if (event.getLastMonth() != null && event.getLastMonth() < startTime.getMonthOfYear()) {
            Timber.d("Filter out %s [after first month]", event);
            return false;
        }

        // event not opened
        if (event.getOpeningHourFrom() != null && event.getOpeningHourFrom() > startTime.getHourOfDay()) {
            Timber.d("Filter out %s [before first hour]", event);
            return false;
        }

        // event has closed
        if (event.getOpeningHourTo() != null && event.getOpeningHourTo() < endTime.getHourOfDay()) {
            Timber.d("Filter out %s [after last hour]", event);
            return false;
        }

        return true;
    }

    private static boolean withinTime(TripEvent event, int timeLeft) {
        return event.getDuration() <= timeLeft;
    }

    private static boolean closeEnough(TripEvent event, @Nullable TripLocation previousEvent) {
        if (previousEvent == null) {
            return true;
        }
        return LocationHelper.getMeterDistance(event, previousEvent) <= 7000;
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

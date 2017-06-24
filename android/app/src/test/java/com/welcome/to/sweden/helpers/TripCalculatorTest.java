package com.welcome.to.sweden.helpers;


import com.welcome.to.sweden.enums.TripObjectType;
import com.welcome.to.sweden.models.MyTripEvent;
import com.welcome.to.sweden.models.MyTripRestaurant;
import com.welcome.to.sweden.objects.TripPath;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

public class TripCalculatorTest {

    @Test
    public void shuffleAndCopyListTest_emptyList() {
        List<String> input = new ArrayList<>();
        List<String> output = TripCalculator.shuffleAndCopyList(input);

        assertEquals(input.size(), 0);
        assertEquals(output.size(), 0);
    }

    @Test
    public void shuffleAndCopyListTest_nullList() {
        List<String> output = TripCalculator.shuffleAndCopyList(null);
        assertNull(output);
    }

    @Test
    public void shuffleAndCopyListTest_twoElements() {
        List<String> input = new ArrayList<>();
        input.add("element 1");
        input.add("element 2");
        List<String> output = TripCalculator.shuffleAndCopyList(input);

        assertEquals(input.size(), 2);
        assertEquals(output.size(), 2);
        assertNotSame(input, output);
    }

    @Test
    public void addNextEventTest_emptyEvents() {
        int timeLeft = 10;
        List<MyTripEvent> availableEvents = new ArrayList<>();
        TripPath tripPath = new TripPath();
        MyTripEvent event = TripCalculator.addNextEvent(timeLeft, availableEvents, tripPath, null);
        int eventDuration = event != null ? event.getDuration() : 0;
        assertEquals(eventDuration, 0);
    }

    @Test
    public void addNextEventTest_tooLongEvents() {
        int timeLeft = 30;
        List<MyTripEvent> availableEvents = new ArrayList<>();
        MyTripEvent event1 = new MyTripEvent();
        event1.setDuration(40);
        MyTripEvent event2 = new MyTripEvent();
        event2.setDuration(50);
        availableEvents.add(event1);
        availableEvents.add(event2);
        TripPath tripPath = new TripPath();

        MyTripEvent event = TripCalculator.addNextEvent(timeLeft, availableEvents, tripPath, null);
        int eventDuration = event != null ? event.getDuration() : 0;

        assertEquals(eventDuration, 0);
        assertEquals(tripPath.getObjectList().size(), 0);
        assertEquals(availableEvents.size(), 2);
    }

    @Test
    public void addNextEventTest_firstTooLongEvent() {
        int timeLeft = 30;
        List<MyTripEvent> availableEvents = new ArrayList<>();
        MyTripEvent event1 = new MyTripEvent();
        event1.setDuration(40);
        MyTripEvent event2 = new MyTripEvent();
        event2.setDuration(20);
        availableEvents.add(event1);
        availableEvents.add(event2);
        TripPath tripPath = new TripPath();
        MyTripEvent event = TripCalculator.addNextEvent(timeLeft, availableEvents, tripPath, null);
        int eventDuration = event != null ? event.getDuration() : 0;

        assertEquals(eventDuration, 20);
        assertEquals(tripPath.getObjectList().size(), 1);
        assertEquals(availableEvents.size(), 1);
    }

    @Test
    public void calculateTrips_emptyEventsAndRestaurants() {
        int days = 3;
        List<MyTripEvent> events = new ArrayList<>();
        List<MyTripRestaurant> restaurants = new ArrayList<>();
        List<TripCalculator.TripPart> tripTemplate = TripCalculator.getDefaultTemplate();
        ArrayList<TripPath> tripPaths =
                TripCalculator.calculateTrips(events, restaurants, days, tripTemplate);

        assertEquals(tripPaths.size(), days);
        for (int i = 0; i < days; i++) {
            assertEquals(tripPaths.get(i).getSelectedObjects(TripObjectType.RESTAURANT).size(), 0);
            assertEquals(tripPaths.get(i).getSelectedObjects(TripObjectType.EVENT).size(), 0);
        }
    }

    @Test
    public void calculateTrips_tooLongEvent() {
        int days = 1;
        List<MyTripEvent> events = new ArrayList<>();
        MyTripEvent event1 = new MyTripEvent();
        event1.setDuration(1000000);
        events.add(event1);
        List<MyTripRestaurant> restaurants = new ArrayList<>();
        List<TripCalculator.TripPart> tripTemplate = TripCalculator.getDefaultTemplate();
        ArrayList<TripPath> tripPaths =
                TripCalculator.calculateTrips(events, restaurants, days, tripTemplate);

        assertEquals(tripPaths.size(), days);
        for (int i = 0; i < days; i++) {
            assertEquals(tripPaths.get(i).getSelectedObjects(TripObjectType.RESTAURANT).size(), 0);
            assertEquals(tripPaths.get(i).getSelectedObjects(TripObjectType.EVENT).size(), 0);
        }
    }

    @Test
    public void calculateTrips_secondEventOkay() {
        int days = 1;
        List<MyTripEvent> events = new ArrayList<>();
        MyTripEvent event1 = new MyTripEvent();
        event1.setDuration(1000000);
        events.add(event1);
        MyTripEvent event2 = new MyTripEvent();
        event2.setDuration(100);
        event2.setId("1337");
        events.add(event2);
        List<MyTripRestaurant> restaurants = new ArrayList<>();
        List<TripCalculator.TripPart> tripTemplate = TripCalculator.getDefaultTemplate();
        ArrayList<TripPath> tripPaths =
                TripCalculator.calculateTrips(events, restaurants, days, tripTemplate);

        assertEquals(tripPaths.size(), days);
        assertEquals(tripPaths.get(0).getSelectedObjects(TripObjectType.RESTAURANT).size(), 0);
        assertEquals(tripPaths.get(0).getSelectedObjects(TripObjectType.EVENT).size(), 1);
        assertEquals(
                tripPaths.get(0).getSelectedObjects(TripObjectType.EVENT).get(0).getId(), "1337");
    }

    @Test
    public void calculateTrips_twoRestaurantsDefaultTemplate() {
        int days = 1;
        List<MyTripEvent> events = new ArrayList<>();
        MyTripEvent event1 = new MyTripEvent();
        event1.setDuration(1000000);
        events.add(event1);
        MyTripEvent event2 = new MyTripEvent();
        event2.setDuration(100);
        event2.setId("1337");
        events.add(event2);
        List<MyTripRestaurant> restaurants = new ArrayList<>();
        restaurants.add(new MyTripRestaurant());
        restaurants.add(new MyTripRestaurant());
        List<TripCalculator.TripPart> tripTemplate = TripCalculator.getDefaultTemplate();
        ArrayList<TripPath> tripPaths =
                TripCalculator.calculateTrips(events, restaurants, days, tripTemplate);

        assertEquals(tripPaths.size(), days);
        assertEquals(tripPaths.get(0).getSelectedObjects(TripObjectType.RESTAURANT).size(), 1);
        assertEquals(tripPaths.get(0).getSelectedObjects(TripObjectType.EVENT).size(), 1);
        assertEquals(
                tripPaths.get(0).getSelectedObjects(TripObjectType.EVENT).get(0).getId(), "1337");
    }

    @Test
    public void calculateTrips_twoRestaurantsExtendedTemplate() {
        int days = 1;
        List<MyTripEvent> events = new ArrayList<>();
        MyTripEvent event1 = new MyTripEvent();
        event1.setDuration(1000000);
        events.add(event1);
        MyTripEvent event2 = new MyTripEvent();
        event2.setDuration(100);
        event2.setId("1337");
        events.add(event2);
        List<MyTripRestaurant> restaurants = new ArrayList<>();
        restaurants.add(new MyTripRestaurant());
        restaurants.add(new MyTripRestaurant());
        List<TripCalculator.TripPart> tripTemplate = TripCalculator.getDefaultTemplate();
        tripTemplate.add(new TripCalculator.TripPart(TripObjectType.RESTAURANT, 60));
        ArrayList<TripPath> tripPaths =
                TripCalculator.calculateTrips(events, restaurants, days, tripTemplate);

        assertEquals(tripPaths.size(), days);
        assertEquals(tripPaths.get(0).getSelectedObjects(TripObjectType.RESTAURANT).size(), 2);
        assertEquals(tripPaths.get(0).getSelectedObjects(TripObjectType.EVENT).size(), 1);
        assertEquals(
                tripPaths.get(0).getSelectedObjects(TripObjectType.EVENT).get(0).getId(), "1337");
    }


}
package com.welcome.to.sweden;

import android.location.Location;

import org.joda.time.DateTime;

import java.util.ArrayList;

import com.welcome.to.sweden.models.cards.TripData;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.objects.TripPath;

public interface Storage {

    void setLocation(Location location);

    Location getLocation();

    DateTime getTripDate();

    void setTripDate(DateTime mTripDate);

    void hasStarted(boolean hasStarted);

    boolean hasStarted();

    TripData getTripData();

    void setMyTrip(TripData content);

    ArrayList<TripPath> getTripPaths();

    void setTripPaths(ArrayList<TripPath> tripPaths);

    void setTripDays(int days);

    int getDays();

    void setCurrency(String currency);

    String getCurrency();

    void setExchangeRates(ExchangeRates exchangeRates);

    ExchangeRates getExchangeRates();
}

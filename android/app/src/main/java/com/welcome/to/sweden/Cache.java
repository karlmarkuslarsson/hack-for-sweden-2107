package com.welcome.to.sweden;

import android.location.Location;

import org.joda.time.DateTime;

import java.util.ArrayList;

import javax.inject.Inject;

import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.models.cards.TripData;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.objects.TripPath;

public class Cache implements Storage {

    private Location mLocation;
    private DateTime mTripDate;
    private boolean mHasStarted;
    private TripData mMyTrip;
    private ArrayList<TripPath> mTripPaths;
    private int mDays;
    private String mCurrency;
    private ExchangeRates mExchangeRates;

    @Inject
    public Cache(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
    }

    @Override
    public void setLocation(Location location) {
        mLocation = location;
    }

    @Override
    public Location getLocation() {
        return mLocation;
    }

    @Override
    public DateTime getTripDate() {
        return mTripDate;
    }

    @Override
    public void setTripDate(DateTime mTripDate) {
        this.mTripDate = mTripDate;
    }

    @Override
    public void hasStarted(boolean hasStarted) {
        mHasStarted = hasStarted;
    }

    @Override
    public boolean hasStarted() {
        return mHasStarted;
    }

    public void setMyTrip(TripData myTrip) {
        mMyTrip = myTrip;
    }

    @Override
    public ArrayList<TripPath> getTripPaths() {
        return mTripPaths;
    }

    @Override
    public void setTripPaths(ArrayList<TripPath> tripPaths) {
        mTripPaths = tripPaths;
    }

    @Override
    public void setTripDays(int days) {
        mDays = days;
    }

    public TripData getTripData() {
        return mMyTrip;
    }

    @Override
    public int getDays() {
        return mDays;
    }

    @Override
    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    @Override
    public String getCurrency() {
        return mCurrency;
    }

    @Override
    public void setExchangeRates(ExchangeRates exchangeRates) {
        mExchangeRates = exchangeRates;
    }

    @Override
    public ExchangeRates getExchangeRates() {
        return mExchangeRates;
    }

}

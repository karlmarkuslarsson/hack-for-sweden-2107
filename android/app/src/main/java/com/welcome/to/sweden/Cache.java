package sweden.hack.userinfo;

import android.location.Location;

import org.joda.time.LocalDate;

import java.util.ArrayList;

import javax.inject.Inject;

import sweden.hack.userinfo.di.InjectionContainer;
import sweden.hack.userinfo.models.cards.myTrip.MyTrip;
import sweden.hack.userinfo.models.exchangerates.ExchangeRates;
import sweden.hack.userinfo.objects.TripPath;

public class Cache implements Storage {

    private Location mLocation;
    private LocalDate mTripDate;
    private boolean mHasStarted;
    private MyTrip mMyTrip;
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
    public LocalDate getTripDate() {
        return mTripDate;
    }

    @Override
    public void setTripDate(LocalDate mTripDate) {
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

    public void setMyTrip(MyTrip myTrip) {
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

    public MyTrip getMyTrip() {
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

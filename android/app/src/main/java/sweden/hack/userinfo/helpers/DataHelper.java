package sweden.hack.userinfo.helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDate;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.di.InjectionContainer;
import sweden.hack.userinfo.models.cards.myTrip.MyTrip;
import sweden.hack.userinfo.models.exchangerates.ExchangeRates;
import sweden.hack.userinfo.objects.TripPath;
import timber.log.Timber;

public class DataHelper {

    @Inject
    Cache mCache;

    @Inject
    Context mContext;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    public DataHelper(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
    }

    public static void setLocation(Location location) {
        throw new RuntimeException("Not implemented!");
    }

    public Location getLocation() {
        throw new RuntimeException("Not implemented!");
    }

    public LocalDate getTripDate() {
        LocalDate date = mCache.getTripDate();
        if (date == null) {
            date = tryReadDate(mSharedPrefsHelper.getPreference(Constants.USER_TRIP_DATE, ""));
            mCache.setTripDate(date);
        }
        return date;
    }

    private LocalDate tryReadDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void setTripDate(LocalDate tripDate) {
        mCache.setTripDate(tripDate);
        mSharedPrefsHelper.setPreference(Constants.USER_TRIP_DATE,
                tripDate != null ? tripDate.toString("yyyy-MM-dd") : null);
    }

    public void hasStarted(boolean hasStarted) {
        mCache.hasStarted(hasStarted);
        mSharedPrefsHelper.setPreference(Constants.USER_HAS_STARTED, hasStarted);
    }

    public boolean hasStarted() {
        return mCache.hasStarted() || mSharedPrefsHelper
                .getPreference(Constants.USER_HAS_STARTED, false);
    }

    public static MyTrip getMyTrip() {
        return null;
    }

    public static void setMyTrip(MyTrip content) {

    }

    public void clear() {
        mSharedPrefsHelper.clearAllPreferences();
        mCache.hasStarted(false);
        mCache.setTripDate(null);
        mCache.setLocation(null);
        mCache.setTripPaths(null);
    }

    public ArrayList<TripPath> getTripPaths() {
        ArrayList<TripPath> tripPaths = mCache.getTripPaths();
        if (tripPaths == null) {
            String paths = mSharedPrefsHelper.getPreference(Constants.TRIP_PATHS, (String) null);
            Type listType = new TypeToken<ArrayList<TripPath>>() {
            }.getType();
            tripPaths = new Gson().fromJson(paths, listType);
            mCache.setTripPaths(tripPaths);
        }
        return tripPaths;
    }

    public void setTripPaths(ArrayList<TripPath> tripPaths) {
        mCache.setTripPaths(tripPaths);
        mSharedPrefsHelper.setPreference(Constants.TRIP_PATHS, new Gson().toJson(tripPaths));
    }

    public void setTripDays(int days) {
        mCache.setTripDays(days);
        mSharedPrefsHelper.setPreference(Constants.USER_TRIP_DAYS, days);
    }

    public int getTripDays() {
        int days = mCache.getDays();
        if (days == 0) {
            days = mSharedPrefsHelper.getPreference(Constants.USER_TRIP_DAYS, 2);
            mCache.setTripDays(days);
        }
        return days;
    }

    public void setCurrency(String currency) {
        mCache.setCurrency(currency);
        mSharedPrefsHelper.setPreference(Constants.USER_CURRENCY, currency);
    }

    public String getCurrency() {
        String currency = mCache.getCurrency();
        if (currency == null) {
            currency = mSharedPrefsHelper
                    .getPreference(Constants.USER_CURRENCY, CurrencyHelper.DEFAULT_CURRENCY);
            mCache.setCurrency(currency);
        }
        return currency;
    }

    public void setExchangeRates(ExchangeRates exchangeRates) {
        mCache.setExchangeRates(exchangeRates);
        mSharedPrefsHelper.setPreference(Constants.CACHED_EXCHANGE_RATES, new Gson()
                .toJson(exchangeRates));
    }

    public ExchangeRates getExchangeRates() {
        ExchangeRates exchangeRates = mCache.getExchangeRates();
        if (exchangeRates == null) {
            String exchangeRateString = mSharedPrefsHelper
                    .getPreference(Constants.CACHED_EXCHANGE_RATES, (String) null);
            if (exchangeRateString == null) {
                try {
                    AssetManager assets = mContext.getAssets();
                    InputStream inputStream = null;
                    inputStream = assets.open("exchange_rates.json");
                    InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
                    exchangeRates = new Gson().fromJson(streamReader, ExchangeRates.class);
                } catch (Exception e) {
                    Timber.e(e);
                    return null;
                }
            } else {
                exchangeRates = new Gson().fromJson(exchangeRateString, ExchangeRates.class);
            }
            mCache.setExchangeRates(exchangeRates);
        }
        return exchangeRates;
    }

}

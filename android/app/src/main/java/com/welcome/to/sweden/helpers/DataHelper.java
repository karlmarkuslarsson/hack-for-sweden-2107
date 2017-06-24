package com.welcome.to.sweden.helpers;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.models.cards.MyTrip;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.HackOfSwedenLocalFilesApi;
import com.welcome.to.sweden.network.exchangerates.ExchangeRatesApi;
import com.welcome.to.sweden.network.response.APIResponse;
import com.welcome.to.sweden.objects.TripPath;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;

public class DataHelper {

    @Inject
    Cache mCache;

    @Inject
    Context mContext;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    HackOfSwedenLocalFilesApi mHackOfSwedenLocalFilesApi;

    @Inject
    ExchangeRatesApi mExchangeRatesApi;

    public DataHelper(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
    }

    public static void setLocation(Location location) {
        throw new RuntimeException("Not implemented!");
    }

    public Location getLocation() {
        throw new RuntimeException("Not implemented!");
    }

    public DateTime getTripDate() {
        DateTime date = mCache.getTripDate();
        if (date == null) {
            date = tryReadDate(mSharedPrefsHelper.getPreference(Constants.USER_TRIP_DATE, ""));
            mCache.setTripDate(date);
        }
        return date;
    }

    private DateTime tryReadDate(String date) {
        try {
            return DateTime.parse(date);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void setTripDate(DateTime tripDate) {
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
                    .getPreference(Constants.USER_CURRENCY, CurrencyHelper.CURRENCY_DEFAULT);
            mCache.setCurrency(currency);
        }
        return currency;
    }

    public void setExchangeRates(ExchangeRates exchangeRates) {
        mCache.setExchangeRates(exchangeRates);
        mSharedPrefsHelper.setPreference(Constants.CACHED_EXCHANGE_RATES, new Gson()
                .toJson(exchangeRates));
    }

    public void getExchangeRates(final Callback<ExchangeRates> callback) {
        ExchangeRates rates = mCache.getExchangeRates();
        if (rates != null) {
            callback.onSuccess(new APIResponse<>(rates, -1));
            return;
        }

        if (mSharedPrefsHelper.contains(Constants.CACHED_EXCHANGE_RATES)) {
            String json = mSharedPrefsHelper.getPreference(Constants.CACHED_EXCHANGE_RATES, "{}");
            rates = new Gson().fromJson(json, ExchangeRates.class);

            // Check date of saved data
            DateTime dt = rates.getDate();
            boolean isOlderThan60Days = dt
                    .withDurationAdded(Duration.standardDays(60), 1)
                    .isAfterNow();

            if (!isOlderThan60Days) {
                callback.onSuccess(new APIResponse<>(rates, -1));
                return;
            }

        }

        getOnlineExchangeRates(callback);
    }

    private void getOnlineExchangeRates(final Callback<ExchangeRates> callback) {
        mExchangeRatesApi.getExchangeRates(new Callback<ExchangeRates>() {
            @Override
            public void onSuccess(@NonNull APIResponse<ExchangeRates> response) {
                ExchangeRates content = response.getContent();
                setExchangeRates(content);
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(@NonNull APIResponse<ExchangeRates> response) {
                // Failed to read from web api, read local version
                getLocalExchangeRates(callback);
            }
        });
    }

    private void getLocalExchangeRates(final Callback<ExchangeRates> callback) {
        mHackOfSwedenLocalFilesApi.getExchangeRates(new Callback<ExchangeRates>() {
            @Override
            public void onSuccess(@NonNull APIResponse<ExchangeRates> response) {
                ExchangeRates content = response.getContent();
                setExchangeRates(content);
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(@NonNull APIResponse<ExchangeRates> response) {
                callback.onFailure(response);
            }
        });
    }

}

package sweden.hack.userinfo.helpers;

import android.content.res.AssetManager;
import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDate;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.Storage;
import sweden.hack.userinfo.models.cards.myTrip.MyTrip;
import sweden.hack.userinfo.models.exchangerates.ExchangeRates;
import sweden.hack.userinfo.objects.TripPath;
import timber.log.Timber;

public class DataHelper {

    public static void setLocation(Location location) {
        throw new RuntimeException("Not implemented!");
    }

    public static Location getLocation() {
        throw new RuntimeException("Not implemented!");
    }

    public static LocalDate getTripDate() {
        LocalDate date = Cache.sharedInstance().getTripDate();
        if (date == null) {
            date = tryReadDate(SharedPrefsHelper.sharedInstance()
                    .getPreference(Constants.USER_TRIP_DATE, ""));
            Cache.sharedInstance().setTripDate(date);
        }
        return date;
    }

    private static LocalDate tryReadDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static void setTripDate(LocalDate tripDate) {
        Cache.sharedInstance().setTripDate(tripDate);
        SharedPrefsHelper.sharedInstance()
                .setPreference(Constants.USER_TRIP_DATE,
                        tripDate != null ? tripDate.toString("yyyy-MM-dd") : null);
    }

    public static void hasStarted(boolean hasStarted) {
        Cache.sharedInstance().hasStarted(hasStarted);
        SharedPrefsHelper.sharedInstance()
                .setPreference(Constants.USER_HAS_STARTED, hasStarted);
    }

    public static boolean hasStarted() {
        return Cache.sharedInstance().hasStarted() || SharedPrefsHelper.sharedInstance()
                .getPreference(Constants.USER_HAS_STARTED, false);
    }

    public static MyTrip getMyTrip() {
        return null;
    }

    public static void setMyTrip(MyTrip content) {

    }

    public static void clear() {
        SharedPrefsHelper.sharedInstance().clearAllPreferences();
        Storage storage = Cache.sharedInstance();
        storage.hasStarted(false);
        storage.setTripDate(null);
        storage.setLocation(null);
        storage.setTripPaths(null);
    }

    public static ArrayList<TripPath> getTripPaths() {
        ArrayList<TripPath> tripPaths = Cache.sharedInstance().getTripPaths();
        if (tripPaths == null) {
            String paths = SharedPrefsHelper.sharedInstance().getPreference(Constants.TRIP_PATHS, (String) null);
            Type listType = new TypeToken<ArrayList<TripPath>>() {
            }.getType();
            tripPaths = new Gson().fromJson(paths, listType);
            Cache.sharedInstance().setTripPaths(tripPaths);
        }
        return tripPaths;
    }

    public static void setTripPaths(ArrayList<TripPath> tripPaths) {
        Cache.sharedInstance().setTripPaths(tripPaths);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.TRIP_PATHS, new Gson().toJson(tripPaths));
    }

    public static void setTripDays(int days) {
        Cache.sharedInstance().setTripDays(days);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.USER_TRIP_DAYS, days);
    }

    public static int getTripDays() {
        int days = Cache.sharedInstance().getDays();
        if (days == 0) {
            days = SharedPrefsHelper.sharedInstance().getPreference(Constants.USER_TRIP_DAYS, 2);
            Cache.sharedInstance().setTripDays(days);
        }
        return days;
    }

    public static void setCurrency(String currency) {
        Cache.sharedInstance().setCurrency(currency);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.USER_CURRENCY, currency);
    }

    public static String getCurrency() {
        String currency = Cache.sharedInstance().getCurrency();
        if (currency == null) {
            currency = SharedPrefsHelper.sharedInstance().getPreference(Constants.USER_CURRENCY, CurrencyHelper.DEFAULT_CURRENCY);
            Cache.sharedInstance().setCurrency(currency);
        }
        return currency;
    }

    public static void setExchangeRates(ExchangeRates exchangeRates) {
        Cache.sharedInstance().setExchangeRates(exchangeRates);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.CACHED_EXCHANGE_RATES, new Gson().toJson(exchangeRates));
    }

    public static ExchangeRates getExchangeRates() {
        ExchangeRates exchangeRates = Cache.sharedInstance().getExchangeRates();
        if (exchangeRates == null) {
            String exchangeRateString = SharedPrefsHelper.sharedInstance().getPreference(Constants.CACHED_EXCHANGE_RATES, (String) null);
            if (exchangeRateString == null) {
                try {
                    AssetManager assets = CustomApplication.sharedInstance().getAssets();
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
            Cache.sharedInstance().setExchangeRates(exchangeRates);
        }
        return exchangeRates;
    }

}

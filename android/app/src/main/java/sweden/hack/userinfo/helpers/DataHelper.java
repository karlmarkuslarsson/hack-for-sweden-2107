package sweden.hack.userinfo.helpers;

import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDate;

import java.lang.reflect.Type;
import java.util.ArrayList;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.Storage;
import sweden.hack.userinfo.models.myTrip.MyTrip;
import sweden.hack.userinfo.objects.TripPath;

public class DataHelper {

    public static void setLocation(Location location) {
        throw new RuntimeException("Not implemented!");
    }

    public static Location getLocation() {
        throw new RuntimeException("Not implemented!");
    }

    public static LocalDate getTripDate() {
        LocalDate date = Cache.sharedInstance().getTripDate();
        return date == null
                ? tryReadDate(SharedPrefsHelper.sharedInstance()
                .getPreference(Constants.USER_TRIP_DATE, ""))
                : date;
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

    public ArrayList<TripPath> getTripPaths() {
        ArrayList<TripPath> tripPaths = Cache.sharedInstance().getTripPaths();
        if (tripPaths == null) {
            String paths = SharedPrefsHelper.sharedInstance().getPreference(Constants.TRIP_PATHS, (String) null);
            Type listType = new TypeToken<ArrayList<TripPath>>() {
            }.getType();
            tripPaths = new Gson().fromJson(paths, listType);
        }
        return tripPaths;
    }

    public void setTripPaths(ArrayList<TripPath> tripPaths) {
        Cache.sharedInstance().setTripPaths(tripPaths);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.TRIP_PATHS, new Gson().toJson(tripPaths));
    }

    public static void setTripDays(int days) {
        Cache.sharedInstance().setTripDays(days);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.USER_TRIP_DAYS, days);
    }

    public int getTripDays() {
        int days = Cache.sharedInstance().getDays();
        return days != 0 ? days : SharedPrefsHelper.sharedInstance().getPreference(Constants.USER_TRIP_DAYS, 2);

    }
}

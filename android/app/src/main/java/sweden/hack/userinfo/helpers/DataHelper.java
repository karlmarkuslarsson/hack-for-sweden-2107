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

public class DataHelper implements Storage {

    @Override
    public void setLocation(Location location) {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public Location getLocation() {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public LocalDate getTripDate() {
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

    @Override
    public void setTripDate(LocalDate tripDate) {
        Cache.sharedInstance().setTripDate(tripDate);
        SharedPrefsHelper.sharedInstance()
                .setPreference(Constants.USER_TRIP_DATE, tripDate.toString());
    }

    @Override
    public void hasStarted(boolean hasStarted) {
        Cache.sharedInstance().hasStarted(hasStarted);
        SharedPrefsHelper.sharedInstance()
                .getPreference(Constants.USER_HAS_STARTED, false);
    }

    @Override
    public boolean hasStarted() {
        return false;
    }

    @Override
    public MyTrip getMyTrip() {
        return null;
    }

    @Override
    public void setMyTrip(MyTrip content) {

    }

    public static void clear() {
        SharedPrefsHelper.sharedInstance().clearAllPreferences();
        Storage storage = Cache.sharedInstance();
        storage.hasStarted(false);
        storage.setTripDate(null);
        storage.setLocation(null);
    }

    @Override
    public ArrayList<TripPath> getTripPaths() {
        ArrayList<TripPath> tripPaths = Cache.sharedInstance().getTripPaths();
        if (tripPaths == null) {
            String paths = SharedPrefsHelper.sharedInstance().getPreference(Constants.TRIP_PATHS, (String) null);
            Type listType = new TypeToken<ArrayList<TripPath>>(){}.getType();
            tripPaths = new Gson().fromJson(paths, listType);
        }
        return tripPaths;
    }

    @Override
    public void setTripPaths(ArrayList<TripPath> tripPaths) {
        Cache.sharedInstance().setTripPaths(tripPaths);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.TRIP_PATHS, new Gson().toJson(tripPaths));
    }
}

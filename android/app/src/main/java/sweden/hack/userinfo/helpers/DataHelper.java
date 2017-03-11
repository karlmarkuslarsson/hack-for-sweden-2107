package sweden.hack.userinfo.helpers;

import android.location.Location;

import org.joda.time.LocalDate;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.Storage;

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

    public static void clear() {
        SharedPrefsHelper.sharedInstance().clearAllPreferences();
        Storage storage = Cache.sharedInstance();
        storage.hasStarted(false);
        storage.setTripDate(null);
        storage.setLocation(null);
    }
}

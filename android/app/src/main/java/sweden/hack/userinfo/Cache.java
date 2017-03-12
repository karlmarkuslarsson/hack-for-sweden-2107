package sweden.hack.userinfo;

import android.location.Location;

import org.joda.time.LocalDate;

import java.util.ArrayList;

import sweden.hack.userinfo.models.myTrip.MyTrip;
import sweden.hack.userinfo.objects.TripPath;

public class Cache implements Storage {

    private static Storage sSharedInstance;

    private Location mLocation;
    private LocalDate mTripDate;
    private boolean mHasStarted;
    private MyTrip mMyTrip;
    private ArrayList<TripPath> mTripPaths;
    private int mDays;

    private Cache() {
    }

    public static Storage sharedInstance() {
        synchronized (Cache.class) {
            if (sSharedInstance == null) {
                sSharedInstance = new Cache();
            }
        }
        return sSharedInstance;
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
}

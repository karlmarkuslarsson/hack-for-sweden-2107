package sweden.hack.userinfo;

import android.location.Location;

import org.joda.time.LocalDate;

public class Cache implements Storage {

    private static Storage sSharedInstance;

    private Location mLocation;
    private LocalDate mTripDate;
    private boolean mHasStarted;

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
}

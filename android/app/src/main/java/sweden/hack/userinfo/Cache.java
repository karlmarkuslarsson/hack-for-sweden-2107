package sweden.hack.userinfo;

import android.location.Location;

public class Cache {

    private static Cache sSharedInstance;
    private String mUserFirstName;
    private String mUserLastName;
    private Location mLocation;
    private String mPersonNumber;

    private Cache() {
    }

    public static Cache sharedInstance() {
        synchronized (Cache.class) {
            if (sSharedInstance == null) {
                sSharedInstance = new Cache();
            }
        }
        return sSharedInstance;
    }

    public void setUserFirstName(String userFirstName) {
        mUserFirstName = userFirstName;
    }

    public String getUserFirstName() {
        return mUserFirstName;
    }

    public void setUserLastName(String userLastName) {
        mUserLastName = userLastName;
    }

    public String getUserLastName() {
        return mUserLastName;
    }

    public void saveLocation(Location location) {
        mLocation = location;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setUserPersonNumber(String personNumber) {
        mPersonNumber = personNumber;
    }

    public String getPersonNumber() {
        return mPersonNumber;
    }

}

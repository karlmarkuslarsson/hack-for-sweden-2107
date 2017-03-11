package sweden.hack.userinfo;

import android.location.Location;

import org.joda.time.LocalDate;

public interface Storage {

    void setLocation(Location location);

    Location getLocation();

    LocalDate getTripDate();

    void setTripDate(LocalDate mTripDate);

    void hasStarted(boolean hasStarted);

    boolean hasStarted();
}

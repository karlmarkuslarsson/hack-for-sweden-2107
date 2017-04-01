package sweden.hack.userinfo;

import android.location.Location;

import org.joda.time.LocalDate;

import java.util.ArrayList;

import sweden.hack.userinfo.models.cards.myTrip.MyTrip;
import sweden.hack.userinfo.objects.TripPath;

public interface Storage {

    void setLocation(Location location);

    Location getLocation();

    LocalDate getTripDate();

    void setTripDate(LocalDate mTripDate);

    void hasStarted(boolean hasStarted);

    boolean hasStarted();

    MyTrip getMyTrip();

    void setMyTrip(MyTrip content);

    ArrayList<TripPath> getTripPaths();

    void setTripPaths(ArrayList<TripPath> tripPaths);

    void setTripDays(int days);

    int getDays();
}

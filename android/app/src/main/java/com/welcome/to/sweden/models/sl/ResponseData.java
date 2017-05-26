package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseData {

    @SerializedName("LatestUpdate")
    private String mLatestUpdate;

    @SerializedName("DataAge")
    private String mDataAge;

    @SerializedName("Buses")
    private List<Route> mBuses;

    @SerializedName("Trains")
    private List<Route> mTrains;

    @SerializedName("Metros")
    private List<Route> mMetros;

    @SerializedName("Trams")
    private List<Route> mTrams;

    @SerializedName("Ships")
    private List<Route> mShips;

    private ArrayList<Route> mAllRoutes;

    public List<Route> getBuses() {
        if (mBuses == null) {
            return new ArrayList<>();
        }
        return mBuses;
    }

    public List<Route> getTrains() {
        if (mTrains == null) {
            return new ArrayList<>();
        }
        return mTrains;
    }

    public List<Route> getMetros() {
        if (mMetros == null) {
            return new ArrayList<>();
        }
        return mMetros;
    }

    public List<Route> getTrams() {
        if (mTrams == null) {
            return new ArrayList<>();
        }
        return mTrams;
    }

    public List<Route> getShips() {
        if (mShips == null) {
            return new ArrayList<>();
        }
        return mShips;
    }

    public synchronized List<Route> getAllRoutes() {
        if (mAllRoutes == null) {
            mAllRoutes = new ArrayList<>();
            mAllRoutes.addAll(getShips());
            mAllRoutes.addAll(getTrams());
            mAllRoutes.addAll(getTrains());
            mAllRoutes.addAll(getBuses());
            mAllRoutes.addAll(getMetros());
        }
        return mAllRoutes;
    }

    public void updateRoutes(HashMap<String, SLRoute> routesMap, String[] filters) {
        List<Route> routes = getAllRoutes();
        for (Route route : routes) {

            String lineNumber = route.getLineNumber();
            if (checkFilter(filters, lineNumber)) {

                SLRoute slRoute;
                if (routesMap.containsKey(lineNumber)) {
                    slRoute = routesMap.get(lineNumber);
                } else {
                    slRoute = new SLRoute(route);
                    routesMap.put(lineNumber, slRoute);
                }
                slRoute.update(route.getDirection(), route.getDestination());
                slRoute.add(route.getDirection(), route.getExpectedDateTime());
            }
        }
    }

    private boolean checkFilter(String[] filters, String lineNumber) {
        if (filters == null) {
            return true;
        }
        for (String filter : filters) {
            if (lineNumber.contains(filter)) {
                return true;
            }
        }
        return false;
    }

}

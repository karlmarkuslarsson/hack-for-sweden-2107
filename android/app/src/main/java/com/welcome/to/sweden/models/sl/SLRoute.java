package com.welcome.to.sweden.models.sl;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.welcome.to.sweden.enums.TransportModeEnum;

public class SLRoute implements Comparable<SLRoute> {

    private final String mLineNumer;
    private final String mStopName;
    private final Route mRoute;
    private final Map<Integer, List<String>> mDirectionTimes;
    private final Map<Integer, String> mDirectionNames;

    public SLRoute(Route route) {
        mRoute = route;
        mDirectionTimes = new HashMap<>();
        mDirectionNames = new HashMap<>();
        mLineNumer = route.getLineNumber();
        mStopName = route.getStopName();
    }

    public void add(int direction, String expectedDateTime) {
        List<String> times;
        if (mDirectionTimes.containsKey(direction)) {
            times = mDirectionTimes.get(direction);
        } else {
            times = new ArrayList<>();
            mDirectionTimes.put(direction, times);
        }
        times.add(expectedDateTime);
    }

    public void update(int direction, String destination) {
        mDirectionNames.put(direction, destination);
    }

    public String getDirectionName(int direction) {
        return mDirectionNames.get(direction) != null ? mDirectionNames.get(direction) : "";
    }

    public String getLineNumber() {
        return mLineNumer != null ? mLineNumer : "";
    }

    public List<String> getDirectionTimes(int direction) {
        List<String> directionTimes = mDirectionTimes.get(direction);
        return directionTimes != null ? directionTimes : new ArrayList<String>();
    }

    public Set<Integer> getDirections() {
        return mDirectionTimes.keySet();
    }

    public String getStopName() {
        return mStopName != null ? mStopName : "";
    }

    @Override
    public int compareTo(@NonNull SLRoute busRoute) {
        return mLineNumer.compareTo(busRoute.getLineNumber());
    }

    public TransportModeEnum getTransportationMode() {
        return mRoute.getTransportMode();
    }

    public String getGroupOfLine() {
        return mRoute.getGroupOfLine();
    }

    public String getTransportationModeName(Context context) {
        return mRoute.getTransportMode().getName(context);
    }

}

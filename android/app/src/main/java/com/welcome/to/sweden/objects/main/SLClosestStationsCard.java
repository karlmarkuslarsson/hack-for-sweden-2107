package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.sl.ClosestStations;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class SLClosestStationsCard extends MainCard {
    private final ClosestStations mClosestStations;

    public SLClosestStationsCard(ClosestStations closestStations) {
        mClosestStations = closestStations;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_SL_CLOSEST_STATIONS;
    }

    public ClosestStations getClosestStations() {
        return mClosestStations;
    }
}

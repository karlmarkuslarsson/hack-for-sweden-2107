package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.sl.ClosestStations;
import com.welcome.to.sweden.objects.main.base.Card;

public class SLClosestStationsCard extends Card {
    private final ClosestStations mClosestStations;

    public SLClosestStationsCard(ClosestStations closestStations) {
        mClosestStations = closestStations;
    }

    public ClosestStations getClosestStations() {
        return mClosestStations;
    }
}

package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.sl.ClosestStations;

public class SLClosestStationsCard extends Card {
    private final ClosestStations mClosestStations;

    public SLClosestStationsCard(ClosestStations closestStations) {
        mClosestStations = closestStations;
    }

    public ClosestStations getClosestStations() {
        return mClosestStations;
    }
}

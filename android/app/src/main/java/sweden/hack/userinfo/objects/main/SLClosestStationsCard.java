package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.sl.ClosestStations;
import sweden.hack.userinfo.objects.main.base.MainCard;

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

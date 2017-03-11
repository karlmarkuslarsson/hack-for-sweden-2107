package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.sl.StopLocation;
import sweden.hack.userinfo.objects.main.SLClosestStationsCard;

public class SLClosestStationsViewHolder extends MainViewHolder<SLClosestStationsCard> {
    private final TextView mTextClosestStation;
    private final TextView mTextClosestStationMeter;

    public SLClosestStationsViewHolder(View root) {
        super(root);
        mTextClosestStation = (TextView) itemView.findViewById(R.id.text_sl_closest_station);
        mTextClosestStationMeter = (TextView) itemView.findViewById(R.id.text_sl_closest_station_meter);
    }

    @Override
    public void init(SLClosestStationsCard card, MainCardListener listener) {
        StopLocation stopLocation = card.getClosestStations().getStopLocations().get(0);
        mTextClosestStation.setText(stopLocation.getName());
        mTextClosestStationMeter.setText(stopLocation.getDist() + " m");
    }

}

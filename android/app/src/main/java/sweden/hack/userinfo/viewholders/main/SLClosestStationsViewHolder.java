package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.sl.StopLocation;
import sweden.hack.userinfo.objects.main.SLClosestStationsCard;

public class SLClosestStationsViewHolder extends MainViewHolder<SLClosestStationsCard> {

    @BindView(R.id.text_sl_closest_station)
    TextView mTextClosestStation;

    @BindView(R.id.text_sl_closest_station_meter)
    TextView mTextClosestStationMeter;

    public SLClosestStationsViewHolder(View root) {
        super(root);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(SLClosestStationsCard card, MainCardListener listener) {
        StopLocation stopLocation = card.getClosestStations().getStopLocations().get(0);
        mTextClosestStation.setText(stopLocation.getName());
        mTextClosestStationMeter.setText(stopLocation.getDist() + " m");
    }

}

package com.welcome.to.sweden.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.sl.StopLocation;
import com.welcome.to.sweden.objects.main.SLClosestStationsCard;

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

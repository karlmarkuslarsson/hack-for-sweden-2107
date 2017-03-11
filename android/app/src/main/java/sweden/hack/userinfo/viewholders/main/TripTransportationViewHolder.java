package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.TripTransportationCard;

public class TripTransportationViewHolder extends MainViewHolder<TripTransportationCard> {

    private final TextView mTransportationTime;

    public TripTransportationViewHolder(View itemView) {
        super(itemView);
        mTransportationTime = (TextView) itemView.findViewById(R.id.transportation_time);
    }

    @Override
    public void init(TripTransportationCard card, MainCardListener listener) {

    }
}

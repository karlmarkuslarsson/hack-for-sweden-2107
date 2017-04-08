package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.TripTransportationCard;

public class TripTransportationViewHolder extends MainViewHolder<TripTransportationCard> {

    @BindView(R.id.transportation_time)
    TextView mTransportationTime;

    public TripTransportationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(TripTransportationCard card, MainCardListener listener) {
        mTransportationTime.setText(card.getTransportTime());
    }

}

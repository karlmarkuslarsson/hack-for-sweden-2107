package com.welcome.to.sweden.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.TripTransportationCard;

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

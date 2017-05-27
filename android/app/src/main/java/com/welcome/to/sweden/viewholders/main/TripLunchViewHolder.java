package com.welcome.to.sweden.viewholders.main;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.TripLunchCard;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripLunchViewHolder extends MainViewHolder<TripLunchCard> {

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.description)
    TextView mDescription;

    @BindView(R.id.tag)
    TextView mTag;

    @BindView(R.id.start_time)
    TextView mStartTime;

    private TripLunchCard mCard;

    public TripLunchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(TripLunchCard card, MainCardListener listener) {
        mCard = card;
        mTitle.setText(R.string.card_lunch_title);
        mDescription.setText(R.string.card_lunch_description);
        mTag.setText(R.string.card_type_lunch);
        mStartTime.setText(card.getStartTime());
        mImage.setImageResource(R.drawable.ic_lunch);
    }

}

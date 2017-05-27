package com.welcome.to.sweden.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.InternetCard;

public class InternetViewHolder extends MainViewHolder<InternetCard> {

    @BindView(R.id.text_internet)
    TextView mTextInternet;

    public InternetViewHolder(View root) {
        super(root);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(InternetCard card, MainCardListener listener) {
        mTextInternet.setText(card.getInternetInfo());
    }

}

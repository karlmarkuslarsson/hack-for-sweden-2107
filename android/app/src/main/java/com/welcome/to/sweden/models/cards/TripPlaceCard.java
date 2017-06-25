package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.helpers.CurrencyHelper;
import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.TripEvent;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;

public class TripPlaceCard extends Card {

    private final TripEvent mTripEvent;
    private final String mStartTime;
    private final String mCurrencyPretty;

    public TripPlaceCard(TripEvent tripEvent, ExchangeRates rates, String currency, String startTime) {
        mStartTime = startTime;
        mTripEvent = tripEvent;
        mCurrencyPretty = CurrencyHelper.toForeignCurrencyPretty(rates, currency, tripEvent.getPrice());
    }

    public TripEvent getTripEvent() {
        return mTripEvent;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public String getPricePretty() {
        return mCurrencyPretty;
    }
}

package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.CurrentCurrency;
import com.welcome.to.sweden.objects.main.base.Card;

public class CurrencyCard extends Card {

    private final CurrentCurrency mCurrentCurrency;

    public CurrencyCard(CurrentCurrency currentCurrency) {
        mCurrentCurrency = currentCurrency;
    }

    public String getCurrencyString() {
        return mCurrentCurrency.getValue();
    }

}

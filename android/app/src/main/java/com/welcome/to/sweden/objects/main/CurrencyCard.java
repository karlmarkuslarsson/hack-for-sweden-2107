package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.CurrentCurrency;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class CurrencyCard extends MainCard {

    private final CurrentCurrency mCurrentCurrency;

    public CurrencyCard(CurrentCurrency currentCurrency) {
        mCurrentCurrency = currentCurrency;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_CURRENCY;
    }

    public String getCurrencyString() {
        return mCurrentCurrency.getValue();
    }

}

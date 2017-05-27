package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.CardComponent;
import com.welcome.to.sweden.models.currency.Currencies;

public class CurrencyCard extends CardComponent {

    private Currencies content;

    public CurrencyCard(Currencies content) {
        this.content = content;
    }

    public String getCurrencyString() {
        return "getCurrencyString()";
    }
}

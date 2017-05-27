package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.CardComponent;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;

public class CurrencyCard extends CardComponent {


    private final ExchangeRates exchangeRates;
    private final String currency;

    public CurrencyCard(ExchangeRates exchangeRates, String currency) {
        this.exchangeRates = exchangeRates;
        this.currency = currency;
    }

    public String getCurrencyString() {
        float rate = exchangeRates.getRate(currency);
        if (rate > 1.0f) {
            return "1 " + currency + " = " + rate + " SEK";
        } else {
            return "1 SEK = " + (1.0f/rate) + " " + currency;
        }
    }
}

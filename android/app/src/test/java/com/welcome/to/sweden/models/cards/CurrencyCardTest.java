package com.welcome.to.sweden.models.cards;

import com.google.common.collect.ImmutableMap;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CurrencyCardTest {
    @Test
    public void getCurrencyStringLower() throws Exception {
        ExchangeRates rate = new ExchangeRates();
        rate.rates = ImmutableMap.of("YEN", 15.0f);
        assertEquals("1 SEK = 15 YEN", new CurrencyCard(rate, "YEN").getCurrencyString());
    }

    @Test
    public void getCurrencyStringHigher() throws Exception {
        ExchangeRates rate = new ExchangeRates();
        rate.rates = ImmutableMap.of("EUR", 0.10491f);
        assertEquals("1 EUR = 9.5 SEK", new CurrencyCard(rate, "EUR").getCurrencyString());
    }

}
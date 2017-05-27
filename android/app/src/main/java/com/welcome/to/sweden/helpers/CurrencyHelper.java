package com.welcome.to.sweden.helpers;

import com.welcome.to.sweden.models.exchangerates.ExchangeRates;

import java.text.DecimalFormat;
import java.util.Locale;

public class CurrencyHelper {

    public static final String CURRENCY_SEK = "SEK";
    public static final String CURRENCY_USD = "USD";
    public static final String CURRENCY_DEFAULT = CURRENCY_USD;

    private CurrencyHelper() {}

    public static float toForeignCurrency(ExchangeRates rate, String currency, int sek) {
        return sek * rate.getRate(currency);
    }

    public static String toForeignCurrencyPretty(ExchangeRates rate, String currency, int sek) {
        float foreignCurrency = toForeignCurrency(rate, currency, sek);
        String formatted = new DecimalFormat("#").format(Math.ceil(foreignCurrency));
        return String.format(Locale.getDefault(), "%s %s", formatted, currency);
    }

    public static String toForeignCurrencyPretty(ExchangeRates rates, String currency, String price) {
        try {
            int p = Integer.parseInt(price);
            return toForeignCurrencyPretty(rates, currency, p);
        } catch (NumberFormatException nfe) {
            return price;
        }
    }
}

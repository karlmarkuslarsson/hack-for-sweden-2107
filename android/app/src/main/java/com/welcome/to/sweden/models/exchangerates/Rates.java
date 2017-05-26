package com.welcome.to.sweden.models.exchangerates;

import java.io.Serializable;

import timber.log.Timber;

public class Rates implements Serializable {

    public String AUD;
    public String BGN;
    public String BRL;
    public String CAD;
    public String CHF;
    public String CNY;
    public String CZK;
    public String DKK;
    public String GBP;
    public String HKD;
    public String HRK;
    public String HUF;
    public String IDR;
    public String ILS;
    public String JPY;
    public String KRW;
    public String MXN;
    public String MYR;
    public String NOK;
    public String NZD;
    public String PHP;
    public String PLN;
    public String RON;
    public String RUB;
    public String SGD;
    public String THB;
    public String TRY;
    public String ZAR;
    public String EUR;
    public String USD;

    public String getExchangeRateString(String currency) {
        switch (currency) {
            case "AUD":
                return AUD;
            case "BGN":
                return BGN;
            case "BRL":
                return BRL;
            case "CAD":
                return CAD;
            case "CHF":
                return CHF;
            case "CNY":
                return CNY;
            case "CZK":
                return CZK;
            case "DKK":
                return DKK;
            case "GBP":
                return GBP;
            case "HKD":
                return HKD;
            case "HRK":
                return HRK;
            case "HUF":
                return HUF;
            case "IDR":
                return IDR;
            case "ILS":
                return ILS;
            case "JPY":
                return JPY;
            case "KRW":
                return KRW;
            case "MXN":
                return MXN;
            case "MYR":
                return MYR;
            case "NOK":
                return NOK;
            case "NZD":
                return NZD;
            case "PHP":
                return PHP;
            case "PLN":
                return PLN;
            case "RON":
                return RON;
            case "RUB":
                return RUB;
            case "SGD":
                return SGD;
            case "THB":
                return THB;
            case "TRY":
                return TRY;
            case "ZAR":
                return ZAR;
            case "EUR":
                return EUR;
            case "USD":
                return USD;

        }
        return null;
    }

    public float getExchangeRate(String currency) {
        String exchangeRateString = getExchangeRateString(currency);
        if (exchangeRateString == null) {
            return -1;

        }
        try {
            return Float.valueOf(exchangeRateString);
        } catch (Exception e) {
            Timber.e(e);
        }
        return -1;
    }

}

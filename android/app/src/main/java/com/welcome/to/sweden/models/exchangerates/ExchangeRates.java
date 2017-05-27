package com.welcome.to.sweden.models.exchangerates;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.Map;

public class ExchangeRates {

    @SerializedName("base")
    public String base;

    @SerializedName("date")
    public String date;

    @SerializedName("rates")
    public Map<String, Float> rates;

    public String getBase() {
        return base;
    }

    public DateTime getDate() {
        return DateTime.parse(date);
    }

    public float getRate(String currency) {
        return rates.containsKey(currency)
                ? rates.get(currency)
                : -1;
    }
}

package com.welcome.to.sweden.models.exchangerates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExchangeRates implements Serializable {
    @SerializedName("rates")
    public Rates mRates;
}

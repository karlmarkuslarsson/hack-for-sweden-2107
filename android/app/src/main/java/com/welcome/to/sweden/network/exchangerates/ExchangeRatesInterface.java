package com.welcome.to.sweden.network.exchangerates;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;

public interface ExchangeRatesInterface {

    @GET("/latest")
    Call<ExchangeRates> getExchangeRates(@Query("base")String base);

}

package com.welcome.to.sweden.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.welcome.to.sweden.models.cards.CurrentCurrencyCard;

public interface CurrencyInterface {

    @GET("currency")
    Call<CurrentCurrencyCard> getCurrency(
            @Query("from_currency") String from,
            @Query("from_value") String value,
            @Query("to_currency") String to);

}

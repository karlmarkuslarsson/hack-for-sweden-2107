package com.welcome.to.sweden.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.welcome.to.sweden.models.cards.holdays.Holidays;

public interface HolidayInterface {

    @GET("holidays")
    Call<Holidays> getHolidays(@Query("date") String date);

}

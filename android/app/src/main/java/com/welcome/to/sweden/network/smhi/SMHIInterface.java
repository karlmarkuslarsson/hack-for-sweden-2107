package com.welcome.to.sweden.network.smhi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.welcome.to.sweden.models.smhi.Weather;

public interface SMHIInterface {

    @GET("lon/{lon}/lat/{lat}/data.json")
    Call<Weather> getWeatherForLatLng(
            @Path("lat") String lat,
            @Path("lon") String lon);
}

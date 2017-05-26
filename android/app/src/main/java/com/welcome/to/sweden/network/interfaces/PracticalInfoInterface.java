package com.welcome.to.sweden.network.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.welcome.to.sweden.models.cards.CardComponent;
import com.welcome.to.sweden.models.cards.myTrip.MyTrip;

public interface PracticalInfoInterface {

    @GET("/practical")
    Call<List<CardComponent>> getPracticalInfo(@Query("currency") String currency);

    @GET("/todo")
    Call<List<CardComponent>> getTodoList(@Query("date") String from, @Query("to") String to);

    @GET("/trip")
    Call<MyTrip> getTripList();
}
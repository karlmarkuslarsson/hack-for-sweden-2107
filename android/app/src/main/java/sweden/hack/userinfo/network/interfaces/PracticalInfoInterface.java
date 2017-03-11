package sweden.hack.userinfo.network.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sweden.hack.userinfo.models.CardComponent;
import sweden.hack.userinfo.models.myTrip.MyTrip;

public interface PracticalInfoInterface {

    @GET("/practical")
    Call<List<CardComponent>> getPracticalInfo(@Query("currency") String currency);

    @GET("/todo")
    Call<List<CardComponent>> getTodoList(@Query("date") String from, @Query("to") String to);

    @GET("/trip")
    Call<MyTrip> getTripList();
}

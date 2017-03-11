package sweden.hack.userinfo.network.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sweden.hack.userinfo.models.CardComponent;

/**
 * Created by sosv on 11/03/17.
 */

public interface PracticalInfoInterface {

    @GET("/practical")
    Call<List<CardComponent>> getPracticalInfo(@Query("currency") String currency);

    @GET("/todo")
    Call<List<CardComponent>> getTodoList(@Query("date") String date);
}

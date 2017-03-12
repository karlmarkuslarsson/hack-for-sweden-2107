package sweden.hack.userinfo.network.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sweden.hack.userinfo.models.population.Population;

public interface PopulationInterface {

    @GET("scb/befolk")
    Call<List<Population>> getPopulation();

}


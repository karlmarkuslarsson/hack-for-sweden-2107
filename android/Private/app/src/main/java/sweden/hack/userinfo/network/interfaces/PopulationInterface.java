package sweden.hack.userinfo.network.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sweden.hack.userinfo.models.population.Population;

/**
 * Created by Markus on 2016-11-12.
 */

public interface PopulationInterface {

    @GET("befolk")
    Call<List<Population>> getPopulation();

}


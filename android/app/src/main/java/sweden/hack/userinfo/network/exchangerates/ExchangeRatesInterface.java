package sweden.hack.userinfo.network.exchangerates;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sweden.hack.userinfo.models.exchangerates.ExchangeRates;

public interface ExchangeRatesInterface {

    @GET("/latest")
    Call<ExchangeRates> getExchangeRates(@Query("base")String base);

}

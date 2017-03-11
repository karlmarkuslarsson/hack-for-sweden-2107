package sweden.hack.userinfo.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sweden.hack.userinfo.models.currency.Currency;

public interface CurrencyInterface {

    @GET("currency")
    Call<Currency> getCurrency(
            @Query("from_currency") String from,
            @Query("from_value") String value,
            @Query("to_currency") String to);

}

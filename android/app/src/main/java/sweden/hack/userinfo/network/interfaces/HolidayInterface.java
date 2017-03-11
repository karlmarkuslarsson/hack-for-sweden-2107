package sweden.hack.userinfo.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sweden.hack.userinfo.models.holdays.Holidays;

public interface HolidayInterface {

    @GET("holidays")
    Call<Holidays> getHolidays(@Query("date") String date);

}

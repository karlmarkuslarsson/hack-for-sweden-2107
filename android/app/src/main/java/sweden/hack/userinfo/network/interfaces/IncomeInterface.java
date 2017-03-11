package sweden.hack.userinfo.network.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sweden.hack.userinfo.models.income.Income;

public interface IncomeInterface {

    @GET("inkomst")
    Call<List<Income>> getIncome();
}

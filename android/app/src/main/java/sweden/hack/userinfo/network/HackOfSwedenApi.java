package sweden.hack.userinfo.network;


import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sweden.hack.userinfo.BuildConfig;
import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.network.interfaces.IncomeInterface;
import sweden.hack.userinfo.network.interfaces.PopulationInterface;
import sweden.hack.userinfo.network.request.CallRequest;

/**
 * Created by Markus on 2016-11-12.
 */

public class HackOfSwedenApi {

    private static HackOfSwedenApi sSharedInstance;
    private PopulationInterface mPopulationApi;
    private IncomeInterface mIncomeApi;

    public static HackOfSwedenApi sharedInstance() {

        synchronized (HackOfSwedenApi.class) {
            if (sSharedInstance == null) {
                sSharedInstance = new HackOfSwedenApi();
            }
        }

        return sSharedInstance;
    }

    public HackOfSwedenApi() {
        init();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://hack-for-sweden.filiplindqvist.com:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mPopulationApi = retrofit.create(PopulationInterface.class);
        mIncomeApi = retrofit.create(IncomeInterface.class);
    }

    public void getPopulation(final Callback<List<Population>> callbackListener) {
        Call<List<Population>> call = mPopulationApi.getPopulation();

        new CallRequest<>(call, callbackListener).execute();
    }

    public void getIncome(final Callback<List<Income>> callbackListener) {
        Call<List<Income>> call = mIncomeApi.getIncome();
        new CallRequest<>(call, callbackListener).execute();
    }
}

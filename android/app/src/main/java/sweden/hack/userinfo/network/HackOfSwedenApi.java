package sweden.hack.userinfo.network;


import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sweden.hack.userinfo.BuildConfig;
import sweden.hack.userinfo.models.CardComponent;
import sweden.hack.userinfo.models.currency.Currency;
import sweden.hack.userinfo.models.holdays.Holidays;
import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.models.phrases.Phrases;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.network.adapters.CardComponentTypeAdapter;
import sweden.hack.userinfo.network.interfaces.CurrencyInterface;
import sweden.hack.userinfo.network.interfaces.HolidayInterface;
import sweden.hack.userinfo.network.interfaces.IncomeInterface;
import sweden.hack.userinfo.network.interfaces.PhrasesInterface;
import sweden.hack.userinfo.network.interfaces.PopulationInterface;
import sweden.hack.userinfo.network.interfaces.PracticalInfoInterface;
import sweden.hack.userinfo.network.request.CallRequest;

/**
 * Created by Markus on 2016-11-12.
 */

public class HackOfSwedenApi {

    private static HackOfSwedenApi sSharedInstance;
    private PopulationInterface mPopulationApi;
    private IncomeInterface mIncomeApi;
    private CurrencyInterface mCurrencyApi;
    private HolidayInterface mHolidayApi;
    private PhrasesInterface mPhrasesApi;

    private PracticalInfoInterface mAllApi;

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

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CardComponent.class, new CardComponentTypeAdapter());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://188.166.26.118:3000")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .client(client)
                .build();

        mPopulationApi = retrofit.create(PopulationInterface.class);
        mIncomeApi = retrofit.create(IncomeInterface.class);
        mCurrencyApi = retrofit.create(CurrencyInterface.class);
        mHolidayApi = retrofit.create(HolidayInterface.class);
        mPhrasesApi = retrofit.create(PhrasesInterface.class);

        mAllApi = retrofit.create(PracticalInfoInterface.class);
    }

    public void getPopulation(final Callback<List<Population>> callbackListener) {
        Call<List<Population>> call = mPopulationApi.getPopulation();

        new CallRequest<>(call, callbackListener).execute();
    }

    public void getIncome(final Callback<List<Income>> callbackListener) {
        Call<List<Income>> call = mIncomeApi.getIncome();
        new CallRequest<>(call, callbackListener).execute();
    }

    public void getCurrency(String fromCurrency, String value, String toCurrency, final Callback<Currency> callbackListener) {
        Call<Currency> call = mCurrencyApi.getCurrency(fromCurrency, value, toCurrency);
        new CallRequest<>(call, callbackListener).execute();

    }

    public void getHolidays(String date, Callback<Holidays> callback) {
        Call<Holidays> call = mHolidayApi.getHolidays(date);
        new CallRequest<>(call, callback).execute();
    }

    public void getPhrases(Callback<Phrases> callback) {
        Call<Phrases> call = mPhrasesApi.getPhrases();
        new CallRequest<>(call, callback).execute();
    }

    public void getPracticalInfo(Callback<List<CardComponent>> callback, String currency) {
        Call<List<CardComponent>> call = mAllApi.getPracticalInfo(currency);
        new CallRequest<>(call, callback).execute();
    }

    public void getTodoList(Callback<List<CardComponent>> callback, String from, String to) {
        Call<List<CardComponent>> call = mAllApi.getTodoList(from, to);
        new CallRequest<>(call, callback).execute();
    }
}

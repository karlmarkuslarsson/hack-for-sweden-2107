package sweden.hack.userinfo.network;


import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sweden.hack.userinfo.BuildConfig;
import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.models.cards.CardComponent;
import sweden.hack.userinfo.models.cards.CurrentCurrency;
import sweden.hack.userinfo.models.cards.holdays.Holidays;
import sweden.hack.userinfo.models.cards.myTrip.MyTrip;
import sweden.hack.userinfo.models.cards.phrases.Phrases;
import sweden.hack.userinfo.models.currency.CountryMap;
import sweden.hack.userinfo.models.currency.Currencies;
import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.network.adapters.CardComponentTypeAdapter;
import sweden.hack.userinfo.network.interfaces.CurrencyInterface;
import sweden.hack.userinfo.network.interfaces.HolidayInterface;
import sweden.hack.userinfo.network.interfaces.IncomeInterface;
import sweden.hack.userinfo.network.interfaces.PhrasesInterface;
import sweden.hack.userinfo.network.interfaces.PopulationInterface;
import sweden.hack.userinfo.network.interfaces.PracticalInfoInterface;
import sweden.hack.userinfo.network.request.CallRequest;
import sweden.hack.userinfo.network.response.APIResponse;

/**
 * Created by Markus on 2016-11-12.
 */
public class HackOfSwedenApi {

    private static final String BASE_URL = "http://188.166.26.118:3000";
    private static HackOfSwedenApi sSharedInstance;

    private PopulationInterface mPopulationApi;
    private IncomeInterface mIncomeApi;
    private CurrencyInterface mCurrencyApi;
    private HolidayInterface mHolidayApi;
    private PhrasesInterface mPhrasesApi;

    private PracticalInfoInterface mAllApi;
    private Gson mGson;

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

        mGson = new GsonBuilder()
                .registerTypeAdapter(CardComponent.class, new CardComponentTypeAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
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

    public void getCurrency(String fromCurrency, String value, String toCurrency, final Callback<CurrentCurrency> callbackListener) {
        Call<CurrentCurrency> call = mCurrencyApi.getCurrency(fromCurrency, value, toCurrency);
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

    public void getTripList(Callback<MyTrip> callback) {
        readJSONFile(callback, "events.json", mGson, MyTrip.class);
    }

    public void getCurrencies(Callback<Currencies> callback) {
        readJSONFile(callback, "currencies.json", mGson, Currencies.class);
    }

    public void getCountryMap(Callback<CountryMap> callback) {
        readJSONFile(callback, "county_number_mapping.json", mGson, CountryMap.class);
    }

    private static <T> void readJSONFile(final Callback<T> callback, final String fileName, final Gson mGson, final Class<T> clz) {
        Observable.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                AssetManager assets = CustomApplication.sharedInstance().getAssets();
                InputStream inputStream = assets.open(fileName);
                InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
                return mGson.fromJson(streamReader, clz);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DefaultObserver<T>() {
            @Override
            public void onNext(T value) {
                callback.onSuccess(new APIResponse<>(value, 200));
            }

            @Override
            public void onError(Throwable e) {
                callback.onFailure(new APIResponse<T>(e));
            }

            @Override
            public void onComplete() {}
        });
    }
}

package com.welcome.to.sweden.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.files.JsonFileReader;
import com.welcome.to.sweden.helpers.HttpInterceptorHelper;
import com.welcome.to.sweden.models.cards.CardComponent;
import com.welcome.to.sweden.models.cards.holdays.Holidays;
import com.welcome.to.sweden.models.cards.myTrip.MyTrip;
import com.welcome.to.sweden.models.cards.phrases.Phrases;
import com.welcome.to.sweden.models.currency.CountryMap;
import com.welcome.to.sweden.models.currency.Currencies;
import com.welcome.to.sweden.models.weather.WeatherStats;
import com.welcome.to.sweden.network.adapters.CardComponentTypeAdapter;
import com.welcome.to.sweden.network.interfaces.HolidayInterface;
import com.welcome.to.sweden.network.interfaces.PhrasesInterface;
import com.welcome.to.sweden.network.interfaces.PracticalInfoInterface;
import com.welcome.to.sweden.network.request.CallRequest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HackOfSwedenApi {

    private static final String BASE_URL = "http://188.166.26.118:3000";

    private HolidayInterface mHolidayApi;
    private PhrasesInterface mPhrasesApi;
    private PracticalInfoInterface mAllApi;
    private JsonFileReader mJsonFileReader;
    private Gson mGson;

    @Inject
    Context mContext;

    @Inject
    @Named("main_scheduler")
    Scheduler mMainScheduler;

    @Inject
    @Named("io_scheduler")
    Scheduler mIOScheduler;

    public HackOfSwedenApi(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
        init();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpInterceptorHelper.setup(builder);
        OkHttpClient client = builder.build();

        mGson = new GsonBuilder()
                .registerTypeAdapter(CardComponent.class, new CardComponentTypeAdapter())
                .create();

        mJsonFileReader = new JsonFileReader(mContext, mGson, mIOScheduler, mMainScheduler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(client)
                .build();

        mHolidayApi = retrofit.create(HolidayInterface.class);
        mPhrasesApi = retrofit.create(PhrasesInterface.class);
        mAllApi = retrofit.create(PracticalInfoInterface.class);
    }

    private static <T> void call(Call<T> call, Callback<T> callback) {
        new CallRequest<>(call, callback).execute();
    }

    public void getHolidays(String date, Callback<Holidays> callback) {
        call(mHolidayApi.getHolidays(date), callback);
    }

    public void getPhrases(Callback<Phrases> callback) {
        call(mPhrasesApi.getPhrases(), callback);
    }

    public void getPracticalInfo(Callback<List<CardComponent>> callback, String currency) {
        call(mAllApi.getPracticalInfo(currency), callback);
    }

    public void getTripList(Callback<MyTrip> callback) {
        mJsonFileReader.read("events.json", MyTrip.class, callback);
    }

    public void getCurrencies(Callback<Currencies> callback) {
        mJsonFileReader.read("currencies.json", Currencies.class, callback);
    }

    public void getWeatherStats(Callback<WeatherStats> callback) {
        mJsonFileReader.read("weather.json", WeatherStats.class, callback);
    }

    public void getCountryMap(Callback<CountryMap> callback) {
        mJsonFileReader.read("county_number_mapping.json", CountryMap.class, callback);
    }
}

package com.welcome.to.sweden.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.files.JsonFileReader;
import com.welcome.to.sweden.helpers.HttpInterceptorHelper;
import com.welcome.to.sweden.models.Holidays;
import com.welcome.to.sweden.models.Phrases;
import com.welcome.to.sweden.models.cards.MyTrip;
import com.welcome.to.sweden.models.cards.base.CardComponent;
import com.welcome.to.sweden.models.currency.CountryMap;
import com.welcome.to.sweden.models.currency.Currencies;
import com.welcome.to.sweden.models.weather.WeatherStats;
import com.welcome.to.sweden.network.adapters.CardComponentTypeAdapter;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

public class HackOfSwedenApi {

    private JsonFileReader mJsonFileReader;

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
        Gson mGson = new GsonBuilder()
                .registerTypeAdapter(CardComponent.class, new CardComponentTypeAdapter())
                .create();
        mJsonFileReader = new JsonFileReader(mContext, mGson, mIOScheduler, mMainScheduler);
    }

    public void getPhrases(Callback<Phrases> callback) {
        mJsonFileReader.read("phrases.json", Phrases.class, callback);
    }
    public void getHolidays(Callback<Holidays> callback) {
        mJsonFileReader.read("holidays.json", Holidays.class, callback);
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

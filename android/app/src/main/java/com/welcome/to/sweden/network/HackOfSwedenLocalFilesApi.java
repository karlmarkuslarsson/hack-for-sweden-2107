package com.welcome.to.sweden.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.files.JsonFileReader;
import com.welcome.to.sweden.helpers.HttpInterceptorHelper;
import com.welcome.to.sweden.models.Holidays;
import com.welcome.to.sweden.models.Phrases;
import com.welcome.to.sweden.models.cards.TripData;
import com.welcome.to.sweden.models.cards.base.CardComponent;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.models.weather.WeatherStats;
import com.welcome.to.sweden.network.adapters.CardComponentTypeAdapter;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

public class HackOfSwedenLocalFilesApi {

    private JsonFileReader mJsonFileReader;

    @Inject
    Context mContext;

    @Inject
    @Named("main_scheduler")
    Scheduler mMainScheduler;

    @Inject
    @Named("io_scheduler")
    Scheduler mIOScheduler;

    public HackOfSwedenLocalFilesApi(InjectionContainer injectionContainer) {
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
        mJsonFileReader.read(Constants.LOCAL_PHRASES_FILE, Phrases.class, callback);
    }
    public void getHolidays(Callback<Holidays> callback) {
        mJsonFileReader.read(Constants.LOCAL_HOLIDAYS_FILE, Holidays.class, callback);
    }

    public void getTripList(Callback<TripData> callback) {
        mJsonFileReader.read(Constants.LOCAL_EVENTS_FILE, TripData.class, callback);
    }

    public void getWeatherStats(Callback<WeatherStats> callback) {
        mJsonFileReader.read(Constants.LOCAL_WEATHER_FILE, WeatherStats.class, callback);
    }

    public void getExchangeRates(Callback<ExchangeRates> callback) {
        mJsonFileReader.read(Constants.LOCAL_EXCHANGE_RATES_FILE, ExchangeRates.class, callback);
    }
}

package com.welcome.to.sweden.network;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welcome.to.sweden.BuildConfig;
import com.welcome.to.sweden.di.InjectionContainer;
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
import com.welcome.to.sweden.network.response.APIResponse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DefaultObserver;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HackOfSwedenApi {

    private static final String BASE_URL = "http://188.166.26.118:3000";

    private HolidayInterface mHolidayApi;
    private PhrasesInterface mPhrasesApi;

    private PracticalInfoInterface mAllApi;
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

        mHolidayApi = retrofit.create(HolidayInterface.class);
        mPhrasesApi = retrofit.create(PhrasesInterface.class);

        mAllApi = retrofit.create(PracticalInfoInterface.class);
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

    public void getTripList(Callback<MyTrip> callback) {
        readJSONFile(callback, "events.json", mGson, MyTrip.class);
    }

    public void getCurrencies(Callback<Currencies> callback) {
        readJSONFile(callback, "currencies.json", mGson, Currencies.class);
    }

    public void getWeatherStats(Callback<WeatherStats> callback) {
        readJSONFile(callback, "weather.json", mGson, WeatherStats.class);
    }

    public void getCountryMap(Callback<CountryMap> callback) {
        readJSONFile(callback, "county_number_mapping.json", mGson, CountryMap.class);
    }

    private <T> void readJSONFile(final Callback<T> callback, final String fileName, final Gson mGson, final Class<T> clz) {
        Observable.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                AssetManager assets = mContext.getAssets();
                InputStream inputStream = assets.open(fileName);
                InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
                return mGson.fromJson(streamReader, clz);
            }
        })
        .subscribeOn(mIOScheduler)
        .observeOn(mMainScheduler)
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
            public void onComplete() {
            }
        });
    }
}

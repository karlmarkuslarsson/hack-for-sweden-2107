package sweden.hack.userinfo.network;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

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
import sweden.hack.userinfo.di.InjectionContainer;
import sweden.hack.userinfo.models.cards.CardComponent;
import sweden.hack.userinfo.models.cards.holdays.Holidays;
import sweden.hack.userinfo.models.cards.myTrip.MyTrip;
import sweden.hack.userinfo.models.cards.phrases.Phrases;
import sweden.hack.userinfo.models.currency.CountryMap;
import sweden.hack.userinfo.models.currency.Currencies;
import sweden.hack.userinfo.network.adapters.CardComponentTypeAdapter;
import sweden.hack.userinfo.network.interfaces.HolidayInterface;
import sweden.hack.userinfo.network.interfaces.PhrasesInterface;
import sweden.hack.userinfo.network.interfaces.PracticalInfoInterface;
import sweden.hack.userinfo.network.request.CallRequest;
import sweden.hack.userinfo.network.response.APIResponse;

public class HackOfSwedenApi {

    private static final String BASE_URL = "http://188.166.26.118:3000";

    private HolidayInterface mHolidayApi;
    private PhrasesInterface mPhrasesApi;

    private PracticalInfoInterface mAllApi;
    private Gson mGson;

    @Inject
    Context mContext;

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
            public void onComplete() {
            }
        });
    }
}

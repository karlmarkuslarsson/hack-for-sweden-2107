package com.welcome.to.sweden.network.exchangerates;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.welcome.to.sweden.BuildConfig;
import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.request.CallRequest;

public class ExchangeRatesApi {

    private ExchangeRatesInterface mApi;

    public ExchangeRatesApi(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
        init();
    }

    public void getExchangeRates(Callback<ExchangeRates> callback) {
        new CallRequest<>(mApi.getExchangeRates("SEK"), callback).execute();
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
                .baseUrl(Constants.EXCHANGE_RATES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mApi = retrofit.create(ExchangeRatesInterface.class);
    }

}

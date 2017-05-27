package com.welcome.to.sweden.network.exchangerates;

import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.helpers.HttpInterceptorHelper;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.request.CallRequest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeRatesApi {

    private static final String SEK = "SEK";

    private ExchangeRatesInterface mApi;

    public ExchangeRatesApi(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
        init();
    }

    public void getExchangeRates(Callback<ExchangeRates> callback) {
        new CallRequest<>(mApi.getExchangeRates(SEK), callback).execute();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpInterceptorHelper.setup(builder);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.EXCHANGE_RATES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mApi = retrofit.create(ExchangeRatesInterface.class);
    }

}

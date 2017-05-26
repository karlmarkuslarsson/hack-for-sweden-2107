package com.welcome.to.sweden.network.smhi;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.welcome.to.sweden.BuildConfig;
import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.models.smhi.Weather;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.request.CallRequest;

public class SMHIApi {

    private SMHIInterface mApi;

    @Inject
    public SMHIApi(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
        init();
    }

    public void getWeatherForLatLng(String lat,
                                    String lon,
                                    final Callback<Weather> callbackListener) {
        new CallRequest<>(mApi.getWeatherForLatLng(lat, lon), callbackListener).execute();
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
                .baseUrl(Constants.SMHI_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mApi = retrofit.create(SMHIInterface.class);
    }

}

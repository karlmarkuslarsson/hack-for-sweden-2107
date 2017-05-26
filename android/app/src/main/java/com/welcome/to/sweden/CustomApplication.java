package com.welcome.to.sweden;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.firebase.analytics.FirebaseAnalytics;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

import com.welcome.to.sweden.di.AndroidDaggerModule;
import com.welcome.to.sweden.di.AppComponent;
import com.welcome.to.sweden.di.CoreDaggerModule;
import com.welcome.to.sweden.di.DaggerAppComponent;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.di.NetworkModule;
import com.welcome.to.sweden.di.StorageModule;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.exchangerates.ExchangeRatesApi;
import com.welcome.to.sweden.network.response.APIResponse;
import timber.log.Timber;

public class CustomApplication extends Application {

    private AppComponent mAppComponent;

    @Inject
    DataHelper mDataHelper;

    @Inject
    ExchangeRatesApi mExchangeRatesApi;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        initDependencyInjection();
        JodaTimeAndroid.init(this);
        initConfiguration();
    }

    private void initDependencyInjection() {
        mAppComponent = DaggerAppComponent.builder()
                .androidDaggerModule(new AndroidDaggerModule(this))
                .coreDaggerModule(new CoreDaggerModule(this))
                .storageModule(new StorageModule(this))
                .networkModule(new NetworkModule(this))
                .build();
        mAppComponent.inject(this);
    }

    private void initConfiguration() {
        mExchangeRatesApi.getExchangeRates(new Callback<ExchangeRates>() {
            @Override
            public void onSuccess(@NonNull APIResponse<ExchangeRates> response) {
                if (response.isSuccessful()) {
                    mDataHelper.setExchangeRates(response.getContent());
                }
            }

            @Override
            public void onFailure(@NonNull APIResponse<ExchangeRates> response) {

            }
        });
    }

    public InjectionContainer getAppComponent() {
        return mAppComponent;
    }

}

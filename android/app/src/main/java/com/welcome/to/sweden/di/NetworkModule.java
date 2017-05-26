package com.welcome.to.sweden.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.network.HackOfSwedenApi;
import com.welcome.to.sweden.network.exchangerates.ExchangeRatesApi;
import com.welcome.to.sweden.network.sl.SLApi;
import com.welcome.to.sweden.network.smhi.SMHIApi;

@Module
public class NetworkModule {
    final private CustomApplication mHomeApplication;

    public NetworkModule(CustomApplication application) {
        mHomeApplication = application;
    }

    @Provides
    @Singleton
    SMHIApi provideSMHIAPI() {
        return new SMHIApi(mHomeApplication.getAppComponent());
    }

    @Provides
    @Singleton
    SLApi provideSLAPI() {
        return new SLApi(mHomeApplication.getAppComponent());
    }

    @Provides
    @Singleton
    HackOfSwedenApi provideHFSApi() {
        return new HackOfSwedenApi(mHomeApplication.getAppComponent());
    }

    @Provides
    @Singleton
    ExchangeRatesApi provideExchangeRatesApi() {
        return new ExchangeRatesApi(mHomeApplication.getAppComponent());
    }

}

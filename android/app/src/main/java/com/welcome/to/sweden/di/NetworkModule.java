package sweden.hack.userinfo.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.exchangerates.ExchangeRatesApi;
import sweden.hack.userinfo.network.sl.SLApi;
import sweden.hack.userinfo.network.smhi.SMHIApi;

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

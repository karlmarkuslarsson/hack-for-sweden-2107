package sweden.hack.userinfo;

import android.app.Application;
import android.support.annotation.NonNull;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

import sweden.hack.userinfo.di.AndroidDaggerModule;
import sweden.hack.userinfo.di.AppComponent;
import sweden.hack.userinfo.di.CoreDaggerModule;
import sweden.hack.userinfo.di.DaggerAppComponent;
import sweden.hack.userinfo.di.InjectionContainer;
import sweden.hack.userinfo.di.NetworkModule;
import sweden.hack.userinfo.di.StorageModule;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.models.exchangerates.ExchangeRates;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.exchangerates.ExchangeRatesApi;
import sweden.hack.userinfo.network.response.APIResponse;
import timber.log.Timber;

public class CustomApplication extends Application {

    private AppComponent mAppComponent;

    @Inject
    DataHelper mDataHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
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
        ExchangeRatesApi.sharedInstance().getExchangeRates(new Callback<ExchangeRates>() {
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

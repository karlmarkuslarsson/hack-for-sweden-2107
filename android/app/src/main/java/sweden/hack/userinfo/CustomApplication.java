package sweden.hack.userinfo;

import android.app.Application;
import android.support.annotation.NonNull;

import net.danlew.android.joda.JodaTimeAndroid;

import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.models.exchangerates.ExchangeRates;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.exchangerates.ExchangeRatesApi;
import sweden.hack.userinfo.network.response.APIResponse;
import timber.log.Timber;

public class CustomApplication extends Application {

    private static CustomApplication sSharedInstance;

    private Storage mCache;
    private DataHelper mDataHelper;

    public CustomApplication() {
    }

    public synchronized static CustomApplication sharedInstance() {
        if (sSharedInstance == null) {
            sSharedInstance = new CustomApplication();
        }

        return sSharedInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        sSharedInstance = this;
        JodaTimeAndroid.init(this);
        mCache = Cache.sharedInstance();
        initConfiguration();
    }

    private void initConfiguration() {
        mDataHelper = new DataHelper();
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

}

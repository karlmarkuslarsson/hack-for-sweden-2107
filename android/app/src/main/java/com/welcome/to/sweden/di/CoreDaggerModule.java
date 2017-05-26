package com.welcome.to.sweden.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.helpers.CurrencyHelper;
import com.welcome.to.sweden.helpers.LocationHelper;

@Module
public class CoreDaggerModule {

    final private CustomApplication mCustomApplication;

    public CoreDaggerModule(CustomApplication application) {
        mCustomApplication = application;
    }

    @Provides
    @Singleton
    LocationHelper provideLocationHelper() {
        return new LocationHelper(mCustomApplication.getAppComponent());
    }

    @Provides
    @Singleton
    CurrencyHelper provideDataHelper() {
        return new CurrencyHelper(mCustomApplication.getAppComponent());
    }

}

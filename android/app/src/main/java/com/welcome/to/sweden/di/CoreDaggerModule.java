package com.welcome.to.sweden.di;

import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.helpers.LocationHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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

}

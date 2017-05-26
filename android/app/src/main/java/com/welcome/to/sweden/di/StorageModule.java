package com.welcome.to.sweden.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.helpers.SharedPrefsHelper;

@Module
public class StorageModule {
    final private CustomApplication mCustomApplication;

    public StorageModule(CustomApplication application) {
        mCustomApplication = application;
    }

    @Provides
    @Singleton
    SharedPrefsHelper provideSharedPrefsHelper() {
        return new SharedPrefsHelper(mCustomApplication.getAppComponent());
    }

    @Provides
    @Singleton
    Cache provideCache() {
        return new Cache(mCustomApplication.getAppComponent());
    }

    @Provides
    @Singleton
    DataHelper provideDataHelper() {
        return new DataHelper(mCustomApplication.getAppComponent());
    }

}

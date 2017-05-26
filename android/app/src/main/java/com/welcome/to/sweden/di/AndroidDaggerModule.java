package com.welcome.to.sweden.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.welcome.to.sweden.CustomApplication;

@Module
public class AndroidDaggerModule {
    final CustomApplication mApplication;

    public AndroidDaggerModule(CustomApplication app) {
        mApplication = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    CustomApplication provideHomeApplication() {
        return mApplication;
    }

}

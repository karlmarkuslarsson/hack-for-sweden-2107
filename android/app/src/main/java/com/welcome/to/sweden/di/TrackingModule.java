package com.welcome.to.sweden.di;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.helpers.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackingModule {

    final private CustomApplication mCustomApplication;

    public TrackingModule(CustomApplication application) {
        mCustomApplication = application;
    }

    @Provides
    @Singleton
    FirebaseAnalytics provideFirebaseAnalytics() {
        return FirebaseAnalytics.getInstance(mCustomApplication);
    }

}

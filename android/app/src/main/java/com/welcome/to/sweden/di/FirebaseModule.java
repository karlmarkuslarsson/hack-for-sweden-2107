package com.welcome.to.sweden.di;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.helpers.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    final private CustomApplication mCustomApplication;

    public FirebaseModule(CustomApplication application) {
        mCustomApplication = application;
    }

    @Provides
    @Singleton
    FirebaseAnalytics provideFirebaseAnalytics() {
        return FirebaseAnalytics.getInstance(mCustomApplication);
    }

    @Provides
    @Singleton
    FirebaseDatabase provideFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    @Singleton
    DatabaseReference provideDatabaseReference(FirebaseDatabase database) {
        return database.getReference("database");
    }

}

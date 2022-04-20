package com.welcome.to.sweden;

import android.app.Application;
import android.content.Context;
import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

import com.welcome.to.sweden.di.AndroidDaggerModule;
import com.welcome.to.sweden.di.AppComponent;
import com.welcome.to.sweden.di.CoreDaggerModule;
import com.welcome.to.sweden.di.DaggerAppComponent;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.di.NetworkModule;
import com.welcome.to.sweden.di.StorageModule;
import com.welcome.to.sweden.di.FirebaseModule;
import com.welcome.to.sweden.helpers.DataHelper;

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
        mDataHelper.loadInitialData();
    }

    private void initDependencyInjection() {
        mAppComponent = DaggerAppComponent.builder()
                .androidDaggerModule(new AndroidDaggerModule(this))
                .coreDaggerModule(new CoreDaggerModule(this))
                .storageModule(new StorageModule(this))
                .firebaseModule(new FirebaseModule(this))
                .networkModule(new NetworkModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public InjectionContainer getAppComponent() {
        return mAppComponent;
    }

}

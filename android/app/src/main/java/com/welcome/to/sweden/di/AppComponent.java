package com.welcome.to.sweden.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AndroidDaggerModule.class, CoreDaggerModule.class, StorageModule.class,
        NetworkModule.class, FirebaseModule.class})
public interface AppComponent extends InjectionContainer {
}

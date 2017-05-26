package com.welcome.to.sweden.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class RxSchedulerModule {

    @Provides
    @Named("main_scheduler")
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @Named("io_scheduler")
    public Scheduler io() {
        return Schedulers.io();
    }

}

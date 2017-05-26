package sweden.hack.userinfo.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sweden.hack.userinfo.CustomApplication;

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

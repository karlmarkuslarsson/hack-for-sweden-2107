package sweden.hack.userinfo.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.helpers.SharedPrefsHelper;

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

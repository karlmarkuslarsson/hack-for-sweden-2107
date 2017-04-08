package sweden.hack.userinfo.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.helpers.CurrencyHelper;
import sweden.hack.userinfo.helpers.LocationHelper;

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

    @Provides
    @Singleton
    CurrencyHelper provideDataHelper() {
        return new CurrencyHelper(mCustomApplication.getAppComponent());
    }

}

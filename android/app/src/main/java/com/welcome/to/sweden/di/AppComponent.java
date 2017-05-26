package sweden.hack.userinfo.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AndroidDaggerModule.class, CoreDaggerModule.class, StorageModule.class,
        NetworkModule.class})
public interface AppComponent extends InjectionContainer {
}

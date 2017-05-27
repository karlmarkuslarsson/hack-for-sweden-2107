package com.welcome.to.sweden.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.welcome.to.sweden.BuildConfig;
import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.di.AndroidDaggerModule;
import com.welcome.to.sweden.di.AppComponent;
import com.welcome.to.sweden.di.CoreDaggerModule;
import com.welcome.to.sweden.di.DaggerAppComponent;
import com.welcome.to.sweden.di.FirebaseModule;
import com.welcome.to.sweden.di.NetworkModule;
import com.welcome.to.sweden.di.RxSchedulerModule;
import com.welcome.to.sweden.di.StorageModule;
import com.welcome.to.sweden.models.Holidays;
import com.welcome.to.sweden.models.Phrases;
import com.welcome.to.sweden.models.cards.MyTrip;
import com.welcome.to.sweden.models.weather.WeatherStats;
import com.welcome.to.sweden.network.response.APIResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = HackOfSwedenLocalFilesApiTest.TesMyApplication.class)
public class HackOfSwedenLocalFilesApiTest {

    private TestScheduler mScheduler = new TestScheduler();
    private HackOfSwedenLocalFilesApi mApi;

    public static class TesMyApplication extends CustomApplication {
        @Override protected void attachBaseContext(Context base) {
            try {
                super.attachBaseContext(base);
            } catch (RuntimeException ignored) {
                // Multidex support doesn't play well with Robolectric yet
            }
        }
    }

    private static abstract class TestCallback<T> implements Callback<T> {
        @Override
        public final void onSuccess(@NonNull APIResponse<T> response) {
            test(response.getContent());
        }

        abstract void test(T content);

        @Override
        public final void onFailure(@NonNull APIResponse<T> response) {
            fail(response.getRawErrorBody());
        }
    }

    @Before
    public void setUp() throws Exception {
        CustomApplication application = (CustomApplication) RuntimeEnvironment.application;

        RxSchedulerModule schedulerModule = mock(RxSchedulerModule.class);
        when(schedulerModule.main()).thenReturn(mScheduler);
        when(schedulerModule.io()).thenReturn(mScheduler);

        CoreDaggerModule coreDaggerModule = new CoreDaggerModule(application);
        StorageModule storageModule = new StorageModule(application);
        FirebaseModule firebaseModule = new FirebaseModule(application);
        NetworkModule networkModule = new NetworkModule(application);
        AndroidDaggerModule androidDaggerModule = new AndroidDaggerModule(application);

        AppComponent component = DaggerAppComponent.builder()
                .rxSchedulerModule(schedulerModule)
                .coreDaggerModule(coreDaggerModule)
                .storageModule(storageModule)
                .firebaseModule(firebaseModule)
                .networkModule(networkModule)
                .androidDaggerModule(androidDaggerModule)
                .build();

        mApi = new HackOfSwedenLocalFilesApi(component);
    }

    @Test
    public void getWeatherStats() throws Exception {
        mApi.getWeatherStats(new TestCallback<WeatherStats>() {
            @Override
            void test(WeatherStats content) {
                assertThat(content.getStats(), hasSize(12));
            }
        });
        mScheduler.triggerActions();
    }

    @Test
    public void getPhrases() throws Exception {
        mApi.getPhrases(new TestCallback<Phrases>() {
            @Override
            void test(Phrases content) {
                assertThat(content.getPhrases(), hasSize(4));
            }
        });
        mScheduler.triggerActions();
    }

    @Test
    public void getTriplist() throws Exception {
        mApi.getTripList(new TestCallback<MyTrip>() {
            @Override
            void test(MyTrip content) {
                assertThat(content.getEvents(), hasSize(24));
                assertThat(content.getRestaurants(), hasSize(8));
            }
        });
        mScheduler.triggerActions();
    }

    @Test
    public void getHolidays() throws Exception {
        mApi.getHolidays(new TestCallback<Holidays>() {
            @Override
            void test(Holidays content) {
                assertThat(content.getHolidays(), hasSize(3));
            }
        });
        mScheduler.triggerActions();
    }

}
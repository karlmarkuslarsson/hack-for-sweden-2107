package sweden.hack.userinfo.di;

import android.content.Context;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.activities.MainActivity;
import sweden.hack.userinfo.activities.StartActivity;
import sweden.hack.userinfo.fragments.AboutFragment;
import sweden.hack.userinfo.fragments.PracticalInfoFragment;
import sweden.hack.userinfo.fragments.TripFragment;
import sweden.hack.userinfo.helpers.CurrencyHelper;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.helpers.LocationHelper;
import sweden.hack.userinfo.helpers.SharedPrefsHelper;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.sl.SLApi;
import sweden.hack.userinfo.network.smhi.SMHIApi;
import sweden.hack.userinfo.viewholders.main.AirportViewHolder;
import sweden.hack.userinfo.viewholders.main.TripPlaceViewHolder;


/**
 * Created by markus on 2017-02-18.
 */

public interface InjectionContainer {

    // Activities
    void inject(MainActivity object);

    // Fragments


    void inject(CustomApplication object);

    void inject(Cache cache);

    void inject(SharedPrefsHelper sharedPrefsHandler);

    // other

    Context applicationContext();

    CustomApplication hybridApplication();

    void inject(SMHIApi object);

    void inject(SLApi object);

    void inject(LocationHelper object);

    void inject(PracticalInfoFragment object);

    void inject(TripFragment object);

    void inject(DataHelper object);

    void inject(HackOfSwedenApi object);

    void inject(StartActivity object);

    void inject(AboutFragment object);

    void inject(CurrencyHelper object);

    void inject(AirportViewHolder object);

    void inject(TripPlaceViewHolder object);

}

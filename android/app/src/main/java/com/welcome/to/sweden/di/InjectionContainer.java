package com.welcome.to.sweden.di;

import android.content.Context;

import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.CustomApplication;
import com.welcome.to.sweden.activities.MainActivity;
import com.welcome.to.sweden.activities.StartActivity;
import com.welcome.to.sweden.dialogs.EventDialog;
import com.welcome.to.sweden.fragments.AboutFragment;
import com.welcome.to.sweden.fragments.PracticalInfoFragment;
import com.welcome.to.sweden.fragments.TripFragment;
import com.welcome.to.sweden.helpers.CurrencyHelper;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.helpers.LocationHelper;
import com.welcome.to.sweden.helpers.SharedPrefsHelper;
import com.welcome.to.sweden.network.HackOfSwedenApi;
import com.welcome.to.sweden.network.exchangerates.ExchangeRatesApi;
import com.welcome.to.sweden.network.sl.SLApi;
import com.welcome.to.sweden.network.smhi.SMHIApi;
import com.welcome.to.sweden.viewholders.main.AirportViewHolder;
import com.welcome.to.sweden.viewholders.main.TripPlaceViewHolder;


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

    void inject(EventDialog object);

    void inject(ExchangeRatesApi object);

}

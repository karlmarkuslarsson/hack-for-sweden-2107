package com.welcome.to.sweden.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.fragments.base.BaseFragment;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.CardComponent;
import com.welcome.to.sweden.models.cards.CurrentCurrency;
import com.welcome.to.sweden.models.cards.holdays.Holidays;
import com.welcome.to.sweden.models.cards.phrases.Phrases;
import com.welcome.to.sweden.models.sl.ClosestStations;
import com.welcome.to.sweden.models.sl.SLTrip;
import com.welcome.to.sweden.models.smhi.Weather;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.HackOfSwedenApi;
import com.welcome.to.sweden.network.response.APIResponse;
import com.welcome.to.sweden.network.sl.SLApi;
import com.welcome.to.sweden.network.smhi.SMHIApi;
import com.welcome.to.sweden.objects.main.AirportCard;
import com.welcome.to.sweden.objects.main.CurrencyCard;
import com.welcome.to.sweden.objects.main.HolidaysCard;
import com.welcome.to.sweden.objects.main.InternetCard;
import com.welcome.to.sweden.objects.main.PhrasesCard;
import com.welcome.to.sweden.objects.main.SLAirportCard;
import com.welcome.to.sweden.objects.main.SLClosestStationsCard;
import com.welcome.to.sweden.objects.main.WeatherCard;
import com.welcome.to.sweden.objects.main.base.MainCard;
import timber.log.Timber;

public class PracticalInfoFragment extends BaseFragment {

    @Inject
    SLApi mSLApi;

    @Inject
    SMHIApi mSMHIApi;

    @Inject
    HackOfSwedenApi mHackOfSwedenApi;

    @Inject
    Cache mCache;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerUtils.getComponent(getContext()).inject(this);
    }

    @Override
    protected void reloadData() {
        mAdapter.reset();
        //addSLCard();
        getAllData();
        //addSLAirportCard();
        addWeatherCard();
        addAirPortCard();

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void addAirPortCard() {
        mAdapter.addCard(new AirportCard());
    }

    private void getAllData() {
        mHackOfSwedenApi.getPracticalInfo(new Callback<List<CardComponent>>() {

            @Override
            public void onSuccess(@NonNull APIResponse<List<CardComponent>> response) {
                for (CardComponent cards : response.getContent()) {
                    if (cards.getType() != null) {
                        switch (cards.getType()) {
                            case CURRENCY:
                                addCurrencyCard((CurrentCurrency) cards);
                                break;
                            case HOLIDAYS:
                                addHolidaysCard((Holidays) cards);
                                break;
                            case PHRASE:
                                addPhrasesCard((Phrases) cards);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull APIResponse<List<CardComponent>> response) {
                Timber.d(response.toString());
            }
        }, "USD");
    }

    private void addInternetCard() {
        mAdapter.addCard(new InternetCard());
    }

    private void addCurrencyCard(CurrentCurrency practicalInfo) {
        mAdapter.addCard(new CurrencyCard(practicalInfo));
    }

    private void addHolidaysCard(Holidays holidays) {
        mAdapter.addCard(new HolidaysCard(holidays));

    }

    private void addPhrasesCard(Phrases phrases) {
        mAdapter.addCard(new PhrasesCard(phrases));
    }

    @Override
    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }

            @Override
            public void dismissCard(MainCard card) {

            }
        };
    }

    private void addSLAirportCard() {
        mSLApi.getTrip(
                Constants.ARLANDA_LAT,
                Constants.ARLANDA_LNG,
                "arlanda",
                Constants.CENTRALEN_LAT,
                Constants.CENTRALEN_LNG,
                "centralen",
                new Callback<SLTrip>() {
                    @Override
                    public void onSuccess(@NonNull APIResponse<SLTrip> response) {
                        SLTrip trip = response.getContent();
                        mAdapter.addCard(new SLAirportCard(trip));
                    }

                    @Override
                    public void onFailure(@NonNull APIResponse<SLTrip> response) {

                    }
                });
    }

    private void addSLCard() {
        Location location = mCache.getLocation();
        if (location != null) {
            mSLApi.getClosestStations(
                    location.getLatitude(),
                    location.getLongitude(),
                    10,
                    SLApi.RADIUS,
                    new Callback<ClosestStations>() {
                        @Override
                        public void onSuccess(@NonNull APIResponse<ClosestStations> response) {
                            ClosestStations closestStations = response.getContent();
                            mAdapter.addCard(new SLClosestStationsCard(closestStations));
                        }

                        @Override
                        public void onFailure(@NonNull APIResponse<ClosestStations> response) {

                        }
                    });
        }
    }

    private void addWeatherCard() {
        Location location = mCache.getLocation();
        if (location != null) {
            String lat = Constants.CENTRALEN_LAT;
            String lon = Constants.CENTRALEN_LNG;
            mSMHIApi.getWeatherForLatLng(
                    String.valueOf(lat),
                    String.valueOf(lon),
                    new Callback<Weather>() {
                        @Override
                        public void onSuccess(@NonNull APIResponse<Weather> response) {

                            Weather weather = response.getContent();
                            mAdapter.addCard(new WeatherCard(weather));
                        }

                        @Override
                        public void onFailure(@NonNull APIResponse<Weather> response) {

                        }
                    });
        }
    }

}

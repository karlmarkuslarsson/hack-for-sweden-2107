package com.welcome.to.sweden.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.fragments.base.BaseFragment;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.Holiday;
import com.welcome.to.sweden.models.Holidays;
import com.welcome.to.sweden.models.Phrase;
import com.welcome.to.sweden.models.Phrases;
import com.welcome.to.sweden.models.cards.AirportCard;
import com.welcome.to.sweden.models.cards.CurrencyCard;
import com.welcome.to.sweden.models.cards.HolidaysCard;
import com.welcome.to.sweden.models.cards.PhrasesCard;
import com.welcome.to.sweden.models.cards.SLAirportCard;
import com.welcome.to.sweden.models.cards.SLClosestStationsCard;
import com.welcome.to.sweden.models.cards.WeatherCard;
import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.currency.Currencies;
import com.welcome.to.sweden.models.sl.ClosestStations;
import com.welcome.to.sweden.models.sl.SLTrip;
import com.welcome.to.sweden.models.smhi.Weather;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.HackOfSwedenApi;
import com.welcome.to.sweden.network.response.APIResponse;
import com.welcome.to.sweden.network.sl.SLApi;
import com.welcome.to.sweden.network.smhi.SMHIApi;

import java.util.List;

import javax.inject.Inject;

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
        getAllData();
        addWeatherCard();
        addAirPortCard();

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void addAirPortCard() {
        mAdapter.addCard(new AirportCard());
    }

    private void getAllData() {
        mHackOfSwedenApi.getHolidays(new Callback<Holidays>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Holidays> response) {
                Holidays holidays = response.getContent();
                List<Holiday> list = holidays.getHolidays();

                addCard(new HolidaysCard(list));
            }

            @Override
            public void onFailure(@NonNull APIResponse<Holidays> response) {
                Timber.e("Failed to get phrases: %s", response.getRawErrorBody());
            }
        });

        mHackOfSwedenApi.getCurrencies(new Callback<Currencies>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Currencies> response) {
                CurrencyCard card = new CurrencyCard(response.getContent());
                addCard(card);
            }

            @Override
            public void onFailure(@NonNull APIResponse<Currencies> response) {
                Timber.e("Failed to get phrases: %s", response.getRawErrorBody());
            }
        });


        mHackOfSwedenApi.getPhrases(new Callback<Phrases>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Phrases> response) {
                Phrases phrases = response.getContent();
                List<Phrase> list = phrases.getPhrases();
                addCard(new PhrasesCard(list));
            }

            @Override
            public void onFailure(@NonNull APIResponse<Phrases> response) {
                Timber.e("Failed to get phrases: %s", response.getRawErrorBody());
            }
        });
    }

    private void addCard(Card phrases) {
        mAdapter.addCard(phrases);
    }

    @Override
    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(Card card) {

            }

            @Override
            public void dismissCard(Card card) {

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

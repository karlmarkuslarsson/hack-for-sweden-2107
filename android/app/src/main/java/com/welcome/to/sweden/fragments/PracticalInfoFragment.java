package com.welcome.to.sweden.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.Constants;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.fragments.base.BaseFragment;
import com.welcome.to.sweden.helpers.AirportData;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.listeners.MainCardListeners;
import com.welcome.to.sweden.models.AirportAlternative;
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
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.models.sl.ClosestStations;
import com.welcome.to.sweden.models.sl.SLTrip;
import com.welcome.to.sweden.models.smhi.Weather;
import com.welcome.to.sweden.network.BasicCallback;
import com.welcome.to.sweden.network.HackOfSwedenLocalFilesApi;
import com.welcome.to.sweden.network.response.APIResponse;
import com.welcome.to.sweden.network.sl.SLApi;
import com.welcome.to.sweden.network.smhi.SMHIApi;

import java.util.List;

import javax.inject.Inject;

public class PracticalInfoFragment extends BaseFragment {

    @Inject
    SLApi mSLApi;

    @Inject
    SMHIApi mSMHIApi;

    @Inject
    HackOfSwedenLocalFilesApi mHackOfSwedenLocalFilesApi;

    @Inject
    Cache mCache;

    @Inject
    DataHelper mDataHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerUtils.getComponent(getContext()).inject(this);
    }

    @Override
    protected void reloadData() {
        mAdapter.reset();
        addCards();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void addCards() {
        mHackOfSwedenLocalFilesApi.getHolidays(new BasicCallback<Holidays>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Holidays> response) {
                Holidays holidays = response.getContent();
                List<Holiday> list = holidays.getHolidays();
                addCard(new HolidaysCard(list));
            }
        });

        mDataHelper.getExchangeRates(new BasicCallback<ExchangeRates>() {
            @Override
            public void onSuccess(@NonNull APIResponse<ExchangeRates> response) {
                ExchangeRates exchangeRates = response.getContent();
                String currency = mDataHelper.getCurrency();
                addCard(new CurrencyCard(exchangeRates, currency));
                List<AirportAlternative> alternatives = AirportData.getAlternatives(exchangeRates, currency);
                addCard(new AirportCard(alternatives));
            }
        });

        mHackOfSwedenLocalFilesApi.getPhrases(new BasicCallback<Phrases>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Phrases> response) {
                Phrases phrases = response.getContent();
                List<Phrase> list = phrases.getPhrases();
                addCard(new PhrasesCard(list));
            }
        });

        addWeatherCard();
    }

    private void addCard(Card phrases) {
        mAdapter.addCard(phrases);
    }

    @Override
    protected MainCardListener getListener() {
        return MainCardListeners.dummy();
    }

    private void addSLAirportCard() {
        mSLApi.getTrip(
                Constants.ARLANDA_LAT,
                Constants.ARLANDA_LNG,
                "arlanda",
                Constants.CENTRALEN_LAT,
                Constants.CENTRALEN_LNG,
                "centralen",
                new BasicCallback<SLTrip>() {
                    @Override
                    public void onSuccess(@NonNull APIResponse<SLTrip> response) {
                        SLTrip trip = response.getContent();
                        mAdapter.addCard(new SLAirportCard(trip));
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
                    new BasicCallback<ClosestStations>() {
                        @Override
                        public void onSuccess(@NonNull APIResponse<ClosestStations> response) {
                            ClosestStations closestStations = response.getContent();
                            mAdapter.addCard(new SLClosestStationsCard(closestStations));
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
                    new BasicCallback<Weather>() {
                        @Override
                        public void onSuccess(@NonNull APIResponse<Weather> response) {
                            Weather weather = response.getContent();
                            mAdapter.addCard(new WeatherCard(weather));
                        }
                    });
        }
    }

}

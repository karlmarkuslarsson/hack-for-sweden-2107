package sweden.hack.userinfo.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.di.DaggerUtils;
import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.cards.CardComponent;
import sweden.hack.userinfo.models.cards.CurrentCurrency;
import sweden.hack.userinfo.models.cards.holdays.Holidays;
import sweden.hack.userinfo.models.cards.phrases.Phrases;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.models.sl.ClosestStations;
import sweden.hack.userinfo.models.sl.SLTrip;
import sweden.hack.userinfo.models.smhi.Weather;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.network.sl.SLApi;
import sweden.hack.userinfo.network.smhi.SMHIApi;
import sweden.hack.userinfo.objects.main.AirportCard;
import sweden.hack.userinfo.objects.main.CurrencyCard;
import sweden.hack.userinfo.objects.main.HolidaysCard;
import sweden.hack.userinfo.objects.main.InternetCard;
import sweden.hack.userinfo.objects.main.PhrasesCard;
import sweden.hack.userinfo.objects.main.PopulationCard;
import sweden.hack.userinfo.objects.main.SLAirportCard;
import sweden.hack.userinfo.objects.main.SLClosestStationsCard;
import sweden.hack.userinfo.objects.main.WeatherCard;
import sweden.hack.userinfo.objects.main.base.MainCard;
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
//        addInternetCard();
        addPopulationCard();

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

    private void addPopulationCard() {
        mHackOfSwedenApi.getPopulation(new Callback<List<Population>>() {
            @Override
            public void onSuccess(@NonNull APIResponse<List<Population>> response) {
                List<Population> populations = response.getContent();
                mAdapter.addCard(new PopulationCard(populations));
            }

            @Override
            public void onFailure(@NonNull APIResponse<List<Population>> response) {

            }
        });
    }
}

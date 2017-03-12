package sweden.hack.userinfo.fragments;

import android.location.Location;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Locale;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.CardComponent;
import sweden.hack.userinfo.models.currency.Currency;
import sweden.hack.userinfo.models.holdays.Holidays;
import sweden.hack.userinfo.models.phrases.Phrases;
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
import sweden.hack.userinfo.objects.main.InternetCard;
import sweden.hack.userinfo.objects.main.HolidaysCard;
import sweden.hack.userinfo.objects.main.PhrasesCard;
import sweden.hack.userinfo.objects.main.SLAirportCard;
import sweden.hack.userinfo.objects.main.SLClosestStationsCard;
import sweden.hack.userinfo.objects.main.WeatherCard;
import sweden.hack.userinfo.objects.main.base.MainCard;
import timber.log.Timber;

public class PracticalInfoFragment extends BaseFragment {

    @Override
    protected void reloadData() {
        mAdapter.reset();
        //addSLCard();
        getAllData();
        addSLAirportCard();
        addWeatherCard();
        addAirPortCard();
//        addInternetCard();

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void addAirPortCard() {
        mAdapter.addCard(new AirportCard());
    }

    private void getAllData() {
        HackOfSwedenApi.sharedInstance().getPracticalInfo(new Callback<List<CardComponent>>() {

            @Override
            public void onSuccess(@NonNull APIResponse<List<CardComponent>> response) {
                for (CardComponent cards : response.getContent()) {
                    if (cards.getType() != null) {
                        switch (cards.getType()) {
                            case CURRENCY:
                                addCurrencyCard((Currency) cards);
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

    private void addCurrencyCard(Currency practicalInfo) {
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
        SLApi.sharedInstance().getTrip(
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
        Location location = Cache.sharedInstance().getLocation();
        if (location != null) {
            SLApi.sharedInstance().getClosestStations(
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
        Location location = Cache.sharedInstance().getLocation();
        if (location != null) {
            String lat = String.format(Locale.US, "%.2f", location.getLatitude());
            String lon = String.format(Locale.US, "%.2f", location.getLongitude());
            SMHIApi.sharedInstance().getWeatherForLatLng(
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

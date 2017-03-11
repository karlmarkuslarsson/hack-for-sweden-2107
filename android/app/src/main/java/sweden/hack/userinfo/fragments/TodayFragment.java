package sweden.hack.userinfo.fragments;

import android.location.Location;
import android.support.annotation.NonNull;

import java.util.Locale;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.sl.ClosestStations;
import sweden.hack.userinfo.models.smhi.Weather;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.network.sl.SLApi;
import sweden.hack.userinfo.network.smhi.SMHIApi;
import sweden.hack.userinfo.objects.main.GenderCard;
import sweden.hack.userinfo.objects.main.SLClosestStationsCard;
import sweden.hack.userinfo.objects.main.WeatherCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TodayFragment extends BaseFragment {

    @Override
    protected void reloadData() {
        mAdapter.reset();
        addWeatherCard();
        addSLCard();
        mSwipeRefreshLayout.setRefreshing(false);

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

    @Override
    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }
        };
    }

    private void addGenderCard() {
        mAdapter.addCard(new GenderCard(DataHelper.getUserPersonNumber()));
    }
}

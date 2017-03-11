package sweden.hack.userinfo.fragments;

import android.location.Location;
import android.support.annotation.NonNull;

import java.util.Locale;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.smhi.Weather;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.network.smhi.SMHIApi;
import sweden.hack.userinfo.objects.main.WeatherCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TodayFragment extends BaseFragment {

    @Override
    protected void reloadData() {
        mAdapter.reset();
        addWeatherCard();
        mSwipeRefreshLayout.setRefreshing(false);
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

}

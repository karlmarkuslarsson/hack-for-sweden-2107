package sweden.hack.userinfo.fragments;

import android.location.Location;
import android.support.annotation.NonNull;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.Locale;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.CardComponent;
import sweden.hack.userinfo.models.smhi.Weather;
import sweden.hack.userinfo.models.todo.Event;
import sweden.hack.userinfo.models.todo.Events;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.network.smhi.SMHIApi;
import sweden.hack.userinfo.objects.main.EventCard;
import sweden.hack.userinfo.objects.main.WeatherCard;
import sweden.hack.userinfo.objects.main.base.MainCard;
import timber.log.Timber;

public class TodoFragment extends BaseFragment {

    @Override
    protected void reloadData() {
        mAdapter.reset();
        getAllData();
        addWeatherCard();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void getAllData() {
        DataHelper dataHelper = new DataHelper();
        LocalDate date = dataHelper.getTripDate();
        String from = date == null ? "" : date.minusDays(2).toString("yyyy-MM-dd");
        String to = date == null ? "" : date.minusDays(10).toString("yyyy-MM-dd");

        HackOfSwedenApi.sharedInstance().getTodoList(new Callback<List<CardComponent>>() {
            @Override
            public void onSuccess(@NonNull APIResponse<List<CardComponent>> response) {
                for (CardComponent card : response.getContent()) {
                    if (card.getType() != null) {
                        switch (card.getType()) {
                            case EVENTS:
                                addEventsCard((Events) card);
                                break;

                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull APIResponse<List<CardComponent>> response) {
                Timber.d(response.toString());
            }
        }, from, to);
    }

    private void addEventsCard(Events card) {
        for (Event e : card.getEvents()){
            mAdapter.addCard(new EventCard(e));
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

            @Override
            public void dismissCard(MainCard card) {

            }
        };
    }

}

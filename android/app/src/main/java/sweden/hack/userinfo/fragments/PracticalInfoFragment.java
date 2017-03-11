package sweden.hack.userinfo.fragments;

import android.location.Location;
import android.support.annotation.NonNull;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.currency.Currency;
import sweden.hack.userinfo.models.holdays.Holidays;
import sweden.hack.userinfo.models.phrases.Phrases;
import sweden.hack.userinfo.models.sl.ClosestStations;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.network.sl.SLApi;
import sweden.hack.userinfo.objects.CurrencyCard;
import sweden.hack.userinfo.objects.InternetCard;
import sweden.hack.userinfo.objects.main.HolidaysCard;
import sweden.hack.userinfo.objects.main.PhrasesCard;
import sweden.hack.userinfo.objects.main.SLClosestStationsCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class PracticalInfoFragment extends BaseFragment {

    @Override
    protected void reloadData() {
        mAdapter.reset();
        //addSLCard();
        addCurrencyCard();
        addInternetCard();
        addHolidaysCard();
        addPhrasesCard();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void addInternetCard() {
        mAdapter.addCard(new InternetCard());
    }

    private void addCurrencyCard() {
        HackOfSwedenApi.sharedInstance().getCurrency("USD", "1", "SEK", new Callback<Currency>() {
            @Override
            public void onSuccess(@NonNull APIResponse<Currency> response) {
                mAdapter.addCard(new CurrencyCard(response.getContent()));
            }

            @Override
            public void onFailure(@NonNull APIResponse<Currency> response) {

            }
        });
    }

    private void addHolidaysCard(){
        HackOfSwedenApi.sharedInstance().getHolidays("20170101", new Callback<Holidays>() {

            @Override
            public void onSuccess(@NonNull APIResponse<Holidays> response) {
                mAdapter.addCard(new HolidaysCard(response.getContent()));
            }

            @Override
            public void onFailure(@NonNull APIResponse<Holidays> response) {

            }
        });
    }

    private void addPhrasesCard() {
        HackOfSwedenApi.sharedInstance().getPhrases(new Callback<Phrases>(){

           @Override
           public void onSuccess(@NonNull APIResponse<Phrases> response) {
               mAdapter.addCard(new PhrasesCard(response.getContent()));
           }
           @Override
           public void onFailure(@NonNull APIResponse<Phrases> response) {
               mAdapter.addCard(new PhrasesCard(response.getContent()));
           }
        });
    }

    @Override
    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }
        };
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
}

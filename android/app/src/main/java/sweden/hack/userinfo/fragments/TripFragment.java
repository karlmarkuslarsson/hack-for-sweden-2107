package sweden.hack.userinfo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.adapters.MainRecyclerViewAdapter;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.helpers.LocationHelper;
import sweden.hack.userinfo.helpers.TripCalculator;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.myTrip.MyTrip;
import sweden.hack.userinfo.models.myTrip.MyTripEvent;
import sweden.hack.userinfo.models.myTrip.MyTripLatLng;
import sweden.hack.userinfo.models.myTrip.MyTripRestaurant;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.objects.TripObject;
import sweden.hack.userinfo.objects.TripPath;
import sweden.hack.userinfo.objects.main.TripFoodCard;
import sweden.hack.userinfo.objects.main.TripPlaceCard;
import sweden.hack.userinfo.objects.main.TripTransportationCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripFragment extends Fragment {

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    protected MainRecyclerViewAdapter mAdapter;
    private View mRoot;
    private ArrayList<TripPath> mTripPath;
    private boolean mUpdateDataOnLoad;
    private MyTrip mMyTripData;
    private int mDays = 1;
    private DataHelper mDataHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_trip, container, false);
        mDataHelper = new DataHelper();
        initViews();
        setupViews();
        initData();
        return mRoot;
    }

    private void initData() {
        mTripPath = mDataHelper.getTripPaths();
        mUpdateDataOnLoad = true;
        if (mTripPath != null) {
            mMyTripData = Cache.sharedInstance().getMyTrip();
            if (mMyTripData != null) {
                mUpdateDataOnLoad = false;
                addTripCards();
            }
        }
        loadData();
    }

    private void setupViews() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });

        setupRecyclerView();
    }

    private void reloadData() {
        mAdapter.reset();
        mUpdateDataOnLoad = true;
        loadData();
    }

    private void setupRecyclerView() {
        mAdapter = new MainRecyclerViewAdapter(getListener());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void loadData() {
        HackOfSwedenApi.sharedInstance().getTripList(new Callback<MyTrip>() {
            @Override
            public void onSuccess(@NonNull APIResponse<MyTrip> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                Cache.sharedInstance().setMyTrip(response.getContent());
                mMyTripData = response.getContent();
                if (mUpdateDataOnLoad) {
                    addTripCards();
                }
            }

            @Override
            public void onFailure(@NonNull APIResponse<MyTrip> response) {

            }
        });
    }

    private void addTripCards() {
        if (mTripPath == null || mTripPath.size() == 0) {
            mTripPath = TripCalculator.calculateTrips(mMyTripData, mDays);
            mDataHelper.setTripPaths(mTripPath);
        }
        for (TripPath tripPath : mTripPath) {
            int startTime = 0;
            int restaurantCounter = 0;
            for (int i = 0; i < tripPath.getObjectList().size(); i++) {
                TripObject currentTrip = tripPath.getObjectList().get(i);
                if (currentTrip == null) {
                    mDataHelper.setTripPaths(null);
                    mTripPath = null;
                    reloadData();
                    return;
                }
                switch (currentTrip.getTripObjectType()) {
                    case RESTAURANT:
                        MyTripRestaurant restaurant = mMyTripData.getRestaurant(currentTrip.getId());
                        if (restaurant == null) {
                            mDataHelper.setTripPaths(null);
                            mTripPath = null;
                            reloadData();
                            return;
                        }
                        mAdapter.addCard(new TripFoodCard(restaurant, getTimeFromTenOClock(startTime)));
                        if (restaurantCounter == 0) {
                            startTime += 60;
                        } else {
                            startTime += 120;
                        }
                        restaurantCounter++;
                        break;
                    case EVENT:
                        MyTripEvent event = mMyTripData.getEvent(currentTrip.getId());
                        if (event == null) {
                            mDataHelper.setTripPaths(null);
                            mTripPath = null;
                            reloadData();
                            return;
                        }
                        mAdapter.addCard(new TripPlaceCard(event, getTimeFromTenOClock(startTime)));
                        startTime += event.getDuration();
                        break;
                    case TRANSFER:
                        if (i != tripPath.getObjectList().size() - 1) {
                            int transportationTime = addTransportation(mMyTripData,
                                    tripPath.getObjectList().get(i - 1),
                                    tripPath.getObjectList().get(i + 1));

                            mAdapter.addCard(new TripTransportationCard(transportationTime + " min"));
                            startTime += transportationTime;
                        }
                        break;
                }
            }
        }
    }

    private int addTransportation(MyTrip mMyTripData, TripObject preObject, TripObject nextObject) {
        MyTripLatLng preTrip = null;
        MyTripLatLng nextTrip = null;
        switch (preObject.getTripObjectType()) {
            case RESTAURANT:
                preTrip = mMyTripData.getRestaurant(preObject.getId());
                break;
            case EVENT:
                preTrip = mMyTripData.getEvent(preObject.getId());
                break;
        }

        switch (nextObject.getTripObjectType()) {
            case RESTAURANT:
                nextTrip = mMyTripData.getRestaurant(nextObject.getId());
                break;
            case EVENT:
                nextTrip = mMyTripData.getEvent(nextObject.getId());
                break;
        }
        float kilometer = LocationHelper.getKilometerDistance(preTrip, nextTrip);
        if (kilometer < 10) {
            return (int) kilometer * 5;
        } else {
            return (int) kilometer * 2;
        }
    }

    private String getTimeFromTenOClock(int startTime) {
        int h = 10;
        int min = 0;
        int hours = startTime / 60;
        int mins = startTime - hours * 60;
        h += hours;
        min += mins;
        return String.format(Locale.US, "%02d:%02d", h, min);
    }

    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }
        };
    }

    private void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRoot.findViewById(R.id.fragment_main_swipe_to_refresh);
        mRecyclerView = (RecyclerView) mRoot.findViewById(R.id.fragment_main_recycler_view);
    }

}

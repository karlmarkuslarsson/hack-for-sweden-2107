package com.welcome.to.sweden.fragments;

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
import java.util.Iterator;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.adapters.MainRecyclerViewAdapter;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.helpers.LocationHelper;
import com.welcome.to.sweden.helpers.TripCalculator;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.myTrip.MyTrip;
import com.welcome.to.sweden.models.cards.myTrip.MyTripEvent;
import com.welcome.to.sweden.models.cards.myTrip.MyTripLatLng;
import com.welcome.to.sweden.models.cards.myTrip.MyTripRestaurant;
import com.welcome.to.sweden.network.Callback;
import com.welcome.to.sweden.network.HackOfSwedenApi;
import com.welcome.to.sweden.network.response.APIResponse;
import com.welcome.to.sweden.objects.TripObject;
import com.welcome.to.sweden.objects.TripPath;
import com.welcome.to.sweden.objects.main.NextDayDivider;
import com.welcome.to.sweden.objects.main.TripDinnerCard;
import com.welcome.to.sweden.objects.main.TripLunchCard;
import com.welcome.to.sweden.objects.main.TripPlaceCard;
import com.welcome.to.sweden.objects.main.TripTransportationCard;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class TripFragment extends Fragment {

    @BindView(R.id.fragment_main_swipe_to_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.fragment_main_recycler_view)
    public RecyclerView mRecyclerView;

    protected MainRecyclerViewAdapter mAdapter;
    private View mRoot;
    private ArrayList<TripPath> mTripPath;
    private boolean mUpdateDataOnLoad;
    private MyTrip mMyTripData;
    private int mDays;

    @Inject
    Cache mCache;

    @Inject
    DataHelper mDataHelper;

    @Inject
    HackOfSwedenApi mHackOfSwedenApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerUtils.getComponent(getContext()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_trip, container, false);
        ButterKnife.bind(this, mRoot);

        mDays = mDataHelper.getTripDays();
        setupViews();
        initData();
        return mRoot;
    }

    private void initData() {
        mTripPath = mDataHelper.getTripPaths();
        mUpdateDataOnLoad = true;
        if (mTripPath != null) {
            mMyTripData = mCache.getMyTrip();
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
        mHackOfSwedenApi.getTripList(new Callback<MyTrip>() {
            @Override
            public void onSuccess(@NonNull APIResponse<MyTrip> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                mCache.setMyTrip(response.getContent());
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
        int counter = 0;
        for (TripPath tripPath : mTripPath) {
            if (mDays > 0) {
                addDividerCard(counter);
            }
            counter++;
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
                        int duration = 120;

                        mAdapter.addCard(new TripDinnerCard(restaurant, getTimeFromTenOClock(startTime), duration));

                        startTime += duration;
                        break;
                    case LUNCH:

                        int lunchDuration = 45;
                        mAdapter.addCard(new TripLunchCard(getTimeFromTenOClock(startTime), lunchDuration));
                        startTime += lunchDuration;

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
                        if (i != tripPath.getObjectList().size() - 1 && i > 0) {
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

    private void addDividerCard(int counter) {
        mAdapter.addCard(new NextDayDivider(counter));
    }

    private int addTransportation(MyTrip mMyTripData, TripObject preObject, TripObject nextObject) {
        MyTripLatLng preTrip = null;
        MyTripLatLng nextTrip = null;
        boolean isLunch = false;
        switch (preObject.getTripObjectType()) {
            case RESTAURANT:
                preTrip = mMyTripData.getRestaurant(preObject.getId());
                break;
            case LUNCH:
                isLunch = true;
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
            case LUNCH:
                isLunch = true;
                break;
        }
        if (isLunch) {
            return 0;
        }
        float kilometer = LocationHelper.getKilometerDistance(preTrip, nextTrip);
        if (kilometer < 10) {
            return (int) (kilometer * 5 * 2);
        } else {
            return (int) (kilometer * 2 * 2);
        }
    }

    private String getTimeFromTenOClock(int startTime) {
        int h = 10;
        int min = 0;
        int hours = startTime / 60;
        int mins = startTime - hours * 60;
        h += hours;
        min += mins;
        h = h % 24;
        return String.format(Locale.US, "%02d:%02d", h, min);
    }

    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {
            }

            @Override
            public void dismissCard(MainCard card) {
                replaceCard(card);
            }
        };
    }

    private void replaceCard(MainCard card) {
        if (card instanceof TripDinnerCard) {
            for (TripPath path : mTripPath) {
                Iterator<TripObject> itr = path.getObjectList().iterator();

                while (itr.hasNext()) {
                    TripObject object = itr.next();
                    if (object.getId() != null && object.getId().equals(((TripDinnerCard) card).getTripRestaurant().getId())) {
                        itr.remove();  // TODO: insert new card here
                        break;
                    }
                }
            }
            for (MyTripRestaurant restaurant : mMyTripData.getRestaurants()) {
                if (!restaurant.getId().equals(((TripDinnerCard) card).getTripRestaurant().getId())) {
                    if (!hasRestaurant(restaurant)) {
                        changeCard(card, new TripDinnerCard(restaurant, ((TripDinnerCard) card).getStartTime(), ((TripDinnerCard) card).getDuration()));
                        return;
                    }
                }
            }

        } else {
            for (TripPath path : mTripPath) {
                Iterator<TripObject> itr = path.getObjectList().iterator();

                while (itr.hasNext()) {
                    TripObject object = itr.next();
                    if (object.getId() != null && object.getId().equals(((TripPlaceCard) card).getTripEvent().getId())) {
                        itr.remove();
                        break;
                    }
                }
            }
            for (MyTripEvent event : mMyTripData.getEvents()) {
                if (!event.getId().equals(((TripPlaceCard) card).getTripEvent().getId())) {// && event.getDuration() <= ((TripPlaceCard) card).getTripEvent().getDuration()) {
                    if (!hasEvent(event)) {
                        changeCard(card, new TripPlaceCard(event, ((TripPlaceCard) card).getStartTime()));
                        return;
                    }
                }
            }

        }
        mAdapter.removeCard(card);
    }

    private boolean hasEvent(MyTripEvent event) {
        for (TripPath path : mTripPath) {
            for (TripObject object : path.getObjectList()) {
                if (object.getId() != null && object.getId().equals(event.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void changeCard(MainCard oldCard, MainCard newCard) {
        mAdapter.changeCard(oldCard, newCard);
    }

    private boolean hasRestaurant(MyTripRestaurant restaurant) {
        for (TripPath path : mTripPath) {
            for (TripObject object : path.getObjectList()) {
                if (object.getId() != null && object.getId().equals(restaurant.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

}

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

import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.adapters.MainRecyclerViewAdapter;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.enums.TripObjectType;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.helpers.LocationHelper;
import com.welcome.to.sweden.helpers.TripCalculator;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.TripEvent;
import com.welcome.to.sweden.models.TripLocation;
import com.welcome.to.sweden.models.TripRestaurant;
import com.welcome.to.sweden.models.cards.TripData;
import com.welcome.to.sweden.models.cards.NextDayDivider;
import com.welcome.to.sweden.models.cards.TripDinnerCard;
import com.welcome.to.sweden.models.cards.TripLunchCard;
import com.welcome.to.sweden.models.cards.TripPlaceCard;
import com.welcome.to.sweden.models.cards.TripTransportationCard;
import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;
import com.welcome.to.sweden.network.BasicCallback;
import com.welcome.to.sweden.network.HackOfSwedenLocalFilesApi;
import com.welcome.to.sweden.network.response.APIResponse;
import com.welcome.to.sweden.objects.TripObject;
import com.welcome.to.sweden.objects.TripPath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripFragment extends Fragment {

    @BindView(R.id.fragment_main_swipe_to_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.fragment_main_recycler_view)
    public RecyclerView mRecyclerView;

    private View mRoot;

    protected MainRecyclerViewAdapter mAdapter;

    private ArrayList<TripPath> mTripPath;
    private boolean mUpdateDataOnLoad;
    private TripData mTripData;
    private int mDays;
    private ExchangeRates mRates;
    private String mCurrency;

    @Inject
    Cache mCache;

    @Inject
    DataHelper mDataHelper;

    @Inject
    HackOfSwedenLocalFilesApi mHackOfSwedenLocalFilesApi;

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

        setupViews();
        initData();
        return mRoot;
    }

    private void initData() {
        mDays = mDataHelper.getNumberOfDaysForTrip();
        mTripPath = mDataHelper.getTripPath();
        mCurrency = mDataHelper.getCurrency();
        mDataHelper.getExchangeRates(new BasicCallback<ExchangeRates>() {
            @Override
            public void onSuccess(@NonNull APIResponse<ExchangeRates> response) {
                mRates = response.getContent();
                startLoad();
            }
        });

    }

    private void startLoad() {
        mUpdateDataOnLoad = true;

        if (mTripPath != null) {
            mTripData = mCache.getTripData();
            if (mTripData != null) {
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
        mAdapter = new MainRecyclerViewAdapter(new MainCardListener() {
            @Override
            public void onCardClick(Card card) {}

            @Override
            public void dismissCard(Card card) {
                replaceCard(card);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void loadData() {
        mHackOfSwedenLocalFilesApi.getTripList(new BasicCallback<TripData>() {
            @Override
            public void onSuccess(@NonNull APIResponse<TripData> response) {
                TripData myTrip = response.getContent();
                mSwipeRefreshLayout.setRefreshing(false);
                mCache.setMyTrip(myTrip);
                mTripData = myTrip;
                if (mUpdateDataOnLoad) {
                    addTripCards();
                }
            }
        });
    }

    private void addTripCards() {
        if (mTripPath == null || mTripPath.size() == 0) {
            mTripPath = TripCalculator.calculateTrips(
                    mTripData,
                    mDays,
                    TripCalculator.getDefaultTemplate(),
                    mDataHelper.getTripDate(),
                    mDataHelper.getTemperature(mDataHelper.getTripDate()));
            mDataHelper.setTripPaths(mTripPath);
        }
        int counter = 0;
        for (TripPath tripPath : mTripPath) {
            if (mDays > 0) {
                addDividerCard(counter);
            }
            counter++;
            int startTime = 0;
            for (int i = 0; i < tripPath.getObjectList().size(); i++) {
                TripObject currentTrip = tripPath.getObjectList().get(i);
                if (currentTrip == null) {
                    mDataHelper.setTripPaths(null);
                    mTripPath = null;
                    reloadData();
                    return;
                }

                Card mainCard = null;
                String objectStartTime = getTimeFromTenOClock(startTime);

                switch (currentTrip.getTripObjectType()) {
                    case RESTAURANT:
                        TripRestaurant restaurant = mTripData.getRestaurant(currentTrip.getId());
                        if (restaurant == null) {
                            onBadTripData();
                            return;
                        }
                        int duration = TripCalculator.RESTAURANT_TIME;
                        mainCard = new TripDinnerCard(restaurant, objectStartTime, duration);
                        startTime += duration;
                        break;
                    case LUNCH:
                        int lunchDuration = TripCalculator.LUNCH_TIME;
                        mainCard = new TripLunchCard(objectStartTime, lunchDuration);
                        startTime += lunchDuration;
                        break;
                    case EVENT:
                        TripEvent event = mTripData.getEvent(currentTrip.getId());
                        if (event == null) {
                            onBadTripData();
                            return;
                        }
                        mainCard = new TripPlaceCard(event, mRates, mCurrency, objectStartTime);
                        startTime += event.getDuration();
                        break;
                    case TRANSFER:
                        if (i != tripPath.getObjectList().size() - 1 && i > 0) {
                            int transportationTime = addTransportation(mTripData, tripPath, i);
                            mainCard = new TripTransportationCard(transportationTime + " min");
                            startTime += transportationTime;
                        }
                        break;
                }
                if (mainCard != null) {
                    mAdapter.addCard(mainCard);
                }
            }
        }
    }

    private void onBadTripData() {
        mDataHelper.setTripPaths(null);
        mTripPath = null;
        reloadData();
    }

    private void addDividerCard(int counter) {
        mAdapter.addCard(new NextDayDivider(counter));
    }

    private int addTransportation(TripData mMyTripData, TripPath tripPath, int position) {
        TripLocation preTrip = null;
        TripLocation nextTrip = null;


        TripObject preObject = tripPath.getObjectList().get(position - 1);
        TripObject nextObject = tripPath.getObjectList().get(position + 1);

        if (preObject.getTripObjectType() == TripObjectType.LUNCH) {
            preObject = tripPath.getObjectList().get(position - 3);
        }
        switch (preObject.getTripObjectType()) {
            case RESTAURANT:
                preTrip = mMyTripData.getRestaurant(preObject.getId());
                break;
            case EVENT:
                preTrip = mMyTripData.getEvent(preObject.getId());
                break;
        }

        boolean isLunch = false;
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
        return LocationHelper.getTimeBetweenEvents(preTrip, nextTrip);
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

    private void replaceCard(Card card) {
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
            for (TripRestaurant restaurant : mTripData.getRestaurants()) {
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
            for (TripEvent event : mTripData.getEvents()) {
                if (!event.getId().equals(((TripPlaceCard) card).getTripEvent().getId())) {// && event.getDuration() <= ((TripPlaceCard) card).getTripEvent().getDuration()) {
                    if (!hasEvent(event)) {
                        String startTime = ((TripPlaceCard) card).getStartTime();
                        changeCard(card, new TripPlaceCard(event, mRates, mCurrency, startTime));
                        return;
                    }
                }
            }

        }
        mAdapter.removeCard(card);
    }

    private boolean hasEvent(TripEvent event) {
        for (TripPath path : mTripPath) {
            for (TripObject object : path.getObjectList()) {
                if (object.getId() != null && object.getId().equals(event.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void changeCard(Card oldCard, Card newCard) {
        mAdapter.changeCard(oldCard, newCard);
    }

    private boolean hasRestaurant(TripRestaurant restaurant) {
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

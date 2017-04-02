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
import java.util.Iterator;
import java.util.Locale;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.adapters.MainRecyclerViewAdapter;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.helpers.LocationHelper;
import sweden.hack.userinfo.helpers.TripCalculator;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.cards.myTrip.MyTrip;
import sweden.hack.userinfo.models.cards.myTrip.MyTripEvent;
import sweden.hack.userinfo.models.cards.myTrip.MyTripLatLng;
import sweden.hack.userinfo.models.cards.myTrip.MyTripRestaurant;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.objects.TripObject;
import sweden.hack.userinfo.objects.TripPath;
import sweden.hack.userinfo.objects.main.NextDayDivider;
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
    private int mDays;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_trip, container, false);
        mDays = DataHelper.getTripDays();
        initViews();
        setupViews();
        initData();
        return mRoot;
    }

    private void initData() {
        mTripPath = DataHelper.getTripPaths();
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
            DataHelper.setTripPaths(mTripPath);
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
                    DataHelper.setTripPaths(null);
                    mTripPath = null;
                    reloadData();
                    return;
                }
                switch (currentTrip.getTripObjectType()) {
                    case RESTAURANT:
                        MyTripRestaurant restaurant = mMyTripData.getRestaurant(currentTrip.getId());
                        if (restaurant == null) {
                            DataHelper.setTripPaths(null);
                            mTripPath = null;
                            reloadData();
                            return;
                        }
                        int duration;
                        if (restaurantCounter == 0) {
                            duration = 60;
                        } else {
                            duration = 120;
                        }
                        mAdapter.addCard(new TripFoodCard(restaurant, getTimeFromTenOClock(startTime), duration));

                        startTime += duration;
                        restaurantCounter++;
                        break;
                    case EVENT:
                        MyTripEvent event = mMyTripData.getEvent(currentTrip.getId());
                        if (event == null) {
                            DataHelper.setTripPaths(null);
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
        if (card instanceof TripFoodCard) {
            for (TripPath path : mTripPath) {
                Iterator<TripObject> itr = path.getObjectList().iterator();

                while (itr.hasNext()) {
                    TripObject object = itr.next();
                    if (object.getId() != null && object.getId().equals(((TripFoodCard) card).getTripRestaurant().getId())) {
                        itr.remove();  // TODO: insert new card here
                        break;
                    }
                }
            }
            for (MyTripRestaurant restaurant : mMyTripData.getRestaurants()) {
                if (!restaurant.getId().equals(((TripFoodCard) card).getTripRestaurant().getId())) {
                    if (!hasRestaurant(restaurant)) {
                        changeCard(card, new TripFoodCard(restaurant, ((TripFoodCard) card).getStartTime(), ((TripFoodCard) card).getDuration()));
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

    private void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRoot.findViewById(R.id.fragment_main_swipe_to_refresh);
        mRecyclerView = (RecyclerView) mRoot.findViewById(R.id.fragment_main_recycler_view);
    }

}

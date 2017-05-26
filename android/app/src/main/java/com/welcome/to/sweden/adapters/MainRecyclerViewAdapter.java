package com.welcome.to.sweden.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.objects.main.base.MainCard;
import com.welcome.to.sweden.viewholders.main.AirportViewHolder;
import com.welcome.to.sweden.viewholders.main.CurrencyViewHolder;
import com.welcome.to.sweden.viewholders.main.HolidaysViewHolder;
import com.welcome.to.sweden.viewholders.main.InternetViewHolder;
import com.welcome.to.sweden.viewholders.main.MainViewHolder;
import com.welcome.to.sweden.viewholders.main.NextDayViewHolder;
import com.welcome.to.sweden.viewholders.main.PhrasesViewHolder;
import com.welcome.to.sweden.viewholders.main.SLAirportTripViewHolder;
import com.welcome.to.sweden.viewholders.main.SLClosestStationsViewHolder;
import com.welcome.to.sweden.viewholders.main.TripFoodPlaceViewHolder;
import com.welcome.to.sweden.viewholders.main.TripPlaceViewHolder;
import com.welcome.to.sweden.viewholders.main.TripTransportationViewHolder;
import com.welcome.to.sweden.viewholders.main.WeatherViewHolder;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private final MainCardListener mListener;
    private List<MainCard> mCards;
    private int mLastPosition = -1;


    public MainRecyclerViewAdapter(MainCardListener listener) {
        mCards = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return mCards.get(position).getViewType();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view holder associated with the view type.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case MainCard.TYPE_WEATHER:
                return new WeatherViewHolder(inflater.inflate(R.layout.card_weather, parent, false));
            case MainCard.TYPE_SL_CLOSEST_STATIONS:
                return new SLClosestStationsViewHolder(inflater.inflate(R.layout.card_sl_closest_stations, parent, false));
            case MainCard.TYPE_CURRENCY:
                return new CurrencyViewHolder(inflater.inflate(R.layout.card_currency, parent, false));
            case MainCard.TYPE_INTERNET:
                return new InternetViewHolder(inflater.inflate(R.layout.card_internet, parent, false));
            case MainCard.TYPE_HOLIDAYS:
                return new HolidaysViewHolder(inflater.inflate(R.layout.card_holidays, parent, false));
            case MainCard.TYPE_PHRASES:
                return new PhrasesViewHolder(inflater.inflate(R.layout.card_phrases, parent, false));
            case MainCard.TYPE_SL_AIRPORT_TRIP:
                return new SLAirportTripViewHolder(inflater.inflate(R.layout.card_sl_airport_trip, parent, false));
            case MainCard.TYPE_TRIP_PLACE:
                return new TripPlaceViewHolder(inflater.inflate(R.layout.card_trip_place, parent, false));
            case MainCard.TYPE_TRIP_FOOD:
                return new TripFoodPlaceViewHolder(inflater.inflate(R.layout.card_trip_food, parent, false));
            case MainCard.TYPE_TRIP_TRANSPORTATION:
                return new TripTransportationViewHolder(inflater.inflate(R.layout.card_trip_transport, parent, false));
            case MainCard.TYPE_AIRPORT:
                return new AirportViewHolder(inflater.inflate(R.layout.card_airport, parent, false));
            case MainCard.TYPE_NEXT_DAY_DIVIDER:
                return new NextDayViewHolder(inflater.inflate(R.layout.card_next_day, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.init(mCards.get(position), mListener);
        addCardAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public void addCard(MainCard card) {
        mCards.add(card);
        notifyItemInserted(mCards.size());
    }

    public void reset() {
        mLastPosition = -1;
        mCards.clear();
        notifyDataSetChanged();
    }

    private void addCardAnimation(View view, int position) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
            view.startAnimation(animation);
            mLastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MainViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }

    public void removeCard(MainCard card) {
        int pos = mCards.indexOf(card);
        mCards.remove(card);
        notifyItemRemoved(pos);
    }

    public void changeCard(MainCard oldCard, MainCard newCard) {
        int pos = mCards.indexOf(oldCard);
        mCards.add(pos, newCard);
        mCards.remove(oldCard);
        notifyItemChanged(pos);
    }
}

package com.welcome.to.sweden.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.common.base.Charsets;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.objects.main.AirportCard;
import com.welcome.to.sweden.objects.main.CurrencyCard;
import com.welcome.to.sweden.objects.main.HolidaysCard;
import com.welcome.to.sweden.objects.main.InternetCard;
import com.welcome.to.sweden.objects.main.NextDayDivider;
import com.welcome.to.sweden.objects.main.PhrasesCard;
import com.welcome.to.sweden.objects.main.SLAirportCard;
import com.welcome.to.sweden.objects.main.SLClosestStationsCard;
import com.welcome.to.sweden.objects.main.TripDinnerCard;
import com.welcome.to.sweden.objects.main.TripLunchCard;
import com.welcome.to.sweden.objects.main.TripPlaceCard;
import com.welcome.to.sweden.objects.main.TripTransportationCard;
import com.welcome.to.sweden.objects.main.WeatherCard;
import com.welcome.to.sweden.objects.main.base.Card;
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
import com.welcome.to.sweden.viewholders.main.TripLunchPlaceViewHolder;
import com.welcome.to.sweden.viewholders.main.TripPlaceViewHolder;
import com.welcome.to.sweden.viewholders.main.TripTransportationViewHolder;
import com.welcome.to.sweden.viewholders.main.WeatherViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private static final HashFunction HASH_FUNCTION = Hashing.goodFastHash(64);

    private final MainCardListener mListener;
    private final BiMap<Class<? extends Card>, Integer> mViewTypes = HashBiMap.create(
            ViewRender.values().length);
    private List<Card> mCards;
    private int mLastPosition = -1;

    enum ViewRender {
        Weather(WeatherViewHolder.class, R.layout.card_weather),
        SLClosestStations(SLClosestStationsViewHolder.class, R.layout.card_sl_closest_stations),
        Currency(CurrencyViewHolder.class, R.layout.card_currency),
        Internet(InternetViewHolder.class, R.layout.card_internet),
        Holidays(HolidaysViewHolder.class, R.layout.card_holidays),
        Phrases(PhrasesViewHolder.class, R.layout.card_phrases),
        SLAirportTrip(SLAirportTripViewHolder.class, R.layout.card_sl_airport_trip),
        TripPlaceView(TripPlaceViewHolder.class, R.layout.card_trip_place),
        TripFoodPlace(TripFoodPlaceViewHolder.class, R.layout.card_trip_food),
        TripLunch(TripLunchPlaceViewHolder.class, R.layout.card_trip_food),
        TripTransportation(TripTransportationViewHolder.class, R.layout.card_trip_transport),
        Airport(AirportViewHolder.class, R.layout.card_airport),
        NextDay(NextDayViewHolder.class, R.layout.card_next_day);

        private final Class<? extends MainViewHolder> viewHolder;
        @LayoutRes private final int layout;

        ViewRender(Class<? extends MainViewHolder> viewHolder, int layout) {
            this.viewHolder = viewHolder;
            this.layout = layout;
        }
    }

    private static final ImmutableMap<Class<? extends Card>, ViewRender> VIEW_MAP =
            ImmutableMap.<Class<? extends Card>, ViewRender>builder()
                    .put(WeatherCard.class, ViewRender.Weather)
                    .put(SLClosestStationsCard.class, ViewRender.SLClosestStations)
                    .put(CurrencyCard.class, ViewRender.Currency)
                    .put(InternetCard.class, ViewRender.Internet)
                    .put(HolidaysCard.class, ViewRender.Holidays)
                    .put(PhrasesCard.class, ViewRender.Phrases)
                    .put(SLAirportCard.class, ViewRender.SLAirportTrip)
                    .put(TripPlaceCard.class, ViewRender.TripPlaceView)
                    .put(TripDinnerCard.class, ViewRender.TripFoodPlace)
                    .put(TripLunchCard.class, ViewRender.TripLunch)
                    .put(TripTransportationCard.class, ViewRender.TripTransportation)
                    .put(AirportCard.class, ViewRender.Airport)
                    .put(NextDayDivider.class, ViewRender.NextDay)
                    .build();

    public MainRecyclerViewAdapter(MainCardListener listener) {
        mCards = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Card card = mCards.get(position);
        String type = card.getClass().getCanonicalName();
        int intType = HASH_FUNCTION.hashString(type, Charsets.UTF_8).asInt();
        mViewTypes.put(card.getClass(), intType);
        return intType;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view holder associated with the view type.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        Class<? extends Card> cardClass = mViewTypes.inverse().get(viewType);
        ViewRender render = VIEW_MAP.get(cardClass);
        View view = inflater.inflate(render.layout, parent, false);
        Class<? extends MainViewHolder> viewHolder = render.viewHolder;

        try {
            Constructor<? extends MainViewHolder> constructor = viewHolder.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Invalid type", e);
        }
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Card card = mCards.get(position);
        holder.init(card, mListener);
        addCardAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public void addCard(Card card) {
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

    public void removeCard(Card card) {
        int pos = mCards.indexOf(card);
        mCards.remove(card);
        notifyItemRemoved(pos);
    }

    public void changeCard(Card oldCard, Card newCard) {
        int pos = mCards.indexOf(oldCard);
        mCards.add(pos, newCard);
        mCards.remove(oldCard);
        notifyItemChanged(pos);
    }
}

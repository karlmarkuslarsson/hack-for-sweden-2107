package com.welcome.to.sweden.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.helpers.CurrencyHelper;
import com.welcome.to.sweden.models.cards.myTrip.MyTripLatLng;
import com.welcome.to.sweden.utils.SpannableUtils;
import com.welcome.to.sweden.utils.TimeUtils;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.welcome.to.sweden.utils.SpannableUtils.title;
import static com.welcome.to.sweden.utils.ViewUtils.text;

public class EventDialog extends Dialog {

    private final MyTripLatLng mTripEvent;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.description)
    TextView mDescription;

    @BindView(R.id.information)
    TextView mInformation;

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.dialog_map)
    MapView mMapView;

    @Inject
    CurrencyHelper mCurrencyHelper;

    public EventDialog(@NonNull Context context, MyTripLatLng tripEvent) {
        super(context);
        setContentView(R.layout.dialog_trip_place);
        ButterKnife.bind(this);
        DaggerUtils.getComponent(getContext()).inject(this);
        mTripEvent = tripEvent;
        initViews();
    }

    private void initViews() {
        mTitle.setText(mTripEvent.getTitle());
        mDescription.setText(mTripEvent.getDescription());
        Glide.with(getContext())
                .load(mTripEvent.getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(mImage);
        putInformation();
        mMapView.onCreate(null);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(12));
                LatLng latLng = new LatLng(mTripEvent.getLatitude(), mTripEvent.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                MarkerOptions markerOption = new MarkerOptions().position(latLng);
                googleMap.addMarker(markerOption);
                googleMap.getUiSettings().setMapToolbarEnabled(false);
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        googleMap.clear();
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                    }
                });
            }
        });
    }

    private void putInformation() {
        String textType = text(getContext(), R.string.label_type);
        String textPrice = text(getContext(), R.string.label_price);
        String textDuration = text(getContext(), R.string.label_duration);

        List<SpannableUtils.TitleValue> titleValueList = new ArrayList<>();
        titleValueList.add(title(textType, WordUtils.capitalize(mTripEvent.getTag())));

        final String price = getPriceString();
        if (price != null) {
            titleValueList.add(title(textPrice, getPriceString()));
        }

        final Integer duration = mTripEvent.getDuration();
        if (duration != null) {
            titleValueList.add(title(textDuration, TimeUtils.getTime(mTripEvent.getDuration())));
        }

        if (!titleValueList.isEmpty()) {
            mInformation.setText(SpannableUtils.boldTitles(
                    titleValueList,
                    SpannableUtils.SeparatorType.NEW_LINE),
                    TextView.BufferType.SPANNABLE);
        } else {
            mInformation.setVisibility(View.GONE);
        }
    }

    private String getPriceString() {
        String priceInSek = mTripEvent.getPrice();
        try {
            int price = Integer.parseInt(priceInSek);
            return mCurrencyHelper.convertToSelectedCurrencyCurrencyString(price);
        } catch (Exception e) {
            return priceInSek;
        }
    }

}

package sweden.hack.userinfo.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.TimeUtils;
import sweden.hack.userinfo.di.DaggerUtils;
import sweden.hack.userinfo.helpers.CurrencyHelper;
import sweden.hack.userinfo.models.cards.myTrip.MyTripEvent;
import sweden.hack.userinfo.utils.SpannableUtils;

public class EventDialog extends Dialog {

    private final MyTripEvent mTripEvent;

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

    public EventDialog(@NonNull Context context, MyTripEvent tripEvent) {
        super(context);
        setContentView(R.layout.dialog_trip_place);
        ButterKnife.bind(this);

        DaggerUtils.getComponent(getContext()).inject(this);

        mTripEvent = tripEvent;

        initViews();
        setupCallbacks();
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
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                LatLng latLng = new LatLng(mTripEvent.getLatitude(), mTripEvent.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                MarkerOptions markerOption = new MarkerOptions().position(latLng);
                googleMap.addMarker(markerOption);
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(11));
                googleMap.getUiSettings().setMapToolbarEnabled(false);

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

        List<SpannableUtils.TitleValue> titleValueList = new ArrayList<>();
        titleValueList.add(new SpannableUtils.TitleValue("Price", getPriceString()));
        titleValueList.add(new SpannableUtils.TitleValue("Duration",
                TimeUtils.getTime(mTripEvent.getDuration())));
        mInformation.setText(SpannableUtils.boldTitle(
                titleValueList, SpannableUtils.SeparatorType.NEW_LINE),
                TextView.BufferType.SPANNABLE);
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

    private void setupCallbacks() {

    }
}

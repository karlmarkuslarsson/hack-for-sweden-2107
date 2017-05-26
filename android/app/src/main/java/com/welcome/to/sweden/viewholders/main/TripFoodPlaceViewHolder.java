package com.welcome.to.sweden.viewholders.main;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.utils.TimeUtils;
import com.welcome.to.sweden.dialogs.EventDialog;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.objects.main.TripFoodCard;

public class TripFoodPlaceViewHolder extends MainViewHolder<TripFoodCard> {

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.description)
    TextView mDescription;

    @BindView(R.id.tag)
    TextView mTag;

    @BindView(R.id.start_time)
    TextView mStartTime;

    @BindView(R.id.event_info)
    TextView mEventInfo;

    private TripFoodCard mCard;

    public TripFoodPlaceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(TripFoodCard card, MainCardListener listener) {
        mCard = card;
        mTitle.setText(card.getTripRestaurant().getTitle());
        mDescription.setText(card.getTripRestaurant().getDescription());
        mTag.setText("restaurant");
        mStartTime.setText(card.getStartTime());
        setEventInfo();

        Glide.with(itemView.getContext())
                .load(card.getTripRestaurant().getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(mImage);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTripPlaceDialog();
            }
        });
    }

    private void showTripPlaceDialog() {
        final EventDialog dialog = new EventDialog(itemView.getContext(), mCard.getTripRestaurant());
        dialog.show();
    }

    private void setEventInfo() {
        StringBuilder sb = new StringBuilder();
        String durationTitle = "Duration: ";
        sb.append(durationTitle);
        sb.append(TimeUtils.getTime(mCard.getDuration()));
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, durationTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mEventInfo.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

}

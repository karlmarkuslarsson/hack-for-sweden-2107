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
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.dialogs.EventDialog;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.objects.main.TripDinnerCard;
import com.welcome.to.sweden.objects.main.TripLunchCard;
import com.welcome.to.sweden.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripLunchPlaceViewHolder extends MainViewHolder<TripLunchCard> {
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

    private TripLunchCard mCard;

    public TripLunchPlaceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(TripLunchCard card, MainCardListener listener) {
        mCard = card;
        mTitle.setText("LUNCH!");
        mDescription.setText("ÄT");
        mTag.setText("restaurant");
        mStartTime.setText(card.getStartTime());
        setEventInfo();


        /*
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTripPlaceDialog();
            }
        });
        */
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

package com.welcome.to.sweden.viewholders.main;

import android.content.Context;
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
import com.welcome.to.sweden.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.welcome.to.sweden.utils.ViewUtils.text;

public class TripDinnerViewHolder extends MainViewHolder<TripDinnerCard> {

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

    private TripDinnerCard mCard;

    public TripDinnerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(TripDinnerCard card, MainCardListener listener) {
        mCard = card;
        mTitle.setText(card.getTripRestaurant().getTitle());
        mDescription.setText(card.getTripRestaurant().getDescription());
        mTag.setText(card.getType());
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
        Context ctx = itemView.getContext();
        StringBuilder sb = new StringBuilder();

        String duration = TimeUtils.getTime(mCard.getDuration());
        int durationLabelLength = text(ctx, R.string.label_duration, "").length();
        sb.append(text(ctx, R.string.label_duration, duration));

        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, durationLabelLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mEventInfo.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

}

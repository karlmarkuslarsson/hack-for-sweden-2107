package com.welcome.to.sweden.viewholders.main;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.common.collect.ImmutableList;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.dialogs.EventDialog;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.TripDinnerCard;
import com.welcome.to.sweden.utils.SpannableUtils;
import com.welcome.to.sweden.utils.SpannableUtils.SeparatorType;
import com.welcome.to.sweden.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.welcome.to.sweden.utils.SpannableUtils.title;
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
        EventDialog.show(itemView.getContext(), mCard.getTripRestaurant());
    }

    private void setEventInfo() {
        Context ctx = itemView.getContext();
        List<SpannableUtils.TitleValue> values = ImmutableList.of(
            title(text(ctx, R.string.label_duration), TimeUtils.getTime(mCard.getDuration()))
        );
        SpannableString text = SpannableUtils.boldTitles(values, SeparatorType.SPACE);
        mEventInfo.setText(text, TextView.BufferType.SPANNABLE);
    }

}

package com.welcome.to.sweden.viewholders.main;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.dialogs.EventDialog;
import com.welcome.to.sweden.helpers.CurrencyHelper;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.myTrip.MyTripEvent;
import com.welcome.to.sweden.objects.main.TripPlaceCard;
import com.welcome.to.sweden.utils.AnimationUtils;
import com.welcome.to.sweden.utils.SpannableUtils;
import com.welcome.to.sweden.utils.SpannableUtils.SeparatorType;
import com.welcome.to.sweden.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.welcome.to.sweden.utils.SpannableUtils.boldTitles;
import static com.welcome.to.sweden.utils.SpannableUtils.title;

public class TripPlaceViewHolder extends MainViewHolder<TripPlaceCard> {

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

    @BindView(R.id.dismiss_background)
    View mDissmissBackground;

    @BindView(R.id.dismiss_yes)
    View mDissmissYes;

    @BindView(R.id.dismiss_no)
    View mDissmissNo;

    private TripPlaceCard mCard;
    private MainCardListener mListener;

    @Inject
    CurrencyHelper mCurrencyHelper;

    public TripPlaceViewHolder(View itemView) {
        super(itemView);
        DaggerUtils.getComponent(itemView.getContext()).inject(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(TripPlaceCard card, final MainCardListener listener) {
        removeDismissView();
        mCard = card;
        mListener = listener;

        MyTripEvent event = card.getTripEvent();
        mTitle.setText(event.getTitle());
        mDescription.setText(event.getDescription());
        mTag.setText(event.getTag());
        mStartTime.setText(card.getStartTime());
        setEventInfo();
        Glide.with(itemView.getContext())
                .load(event.getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(mImage);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setDismissView();
                return true;
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTripPlaceDialog();
            }
        });
    }

    private void setDismissView() {
        AnimationUtils.fadeIn(mDissmissBackground, 500);
        mDissmissYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.dismissCard(mCard);
            }
        });

        mDissmissNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDismissView();
            }
        });
    }

    private void removeDismissView() {
        mDissmissBackground.setVisibility(View.GONE);
    }

    private void setEventInfo() {
        List<SpannableUtils.TitleValue> titleValueList = new ArrayList<>();
        titleValueList.add(title("Price", getPriceString()));
        titleValueList.add(title("Duration",
                TimeUtils.getTime(mCard.getTripEvent().getDuration())));

        mEventInfo.setText(boldTitles(titleValueList, SeparatorType.SPACE), TextView.BufferType.SPANNABLE);
    }

    private String getPriceString() {
        String priceInSek = mCard.getTripEvent().getPrice();
        try {
            int price = Integer.parseInt(priceInSek);
            return mCurrencyHelper.convertToSelectedCurrencyCurrencyString(price);
        } catch (Exception e) {
            return priceInSek;
        }
    }

    private void showTripPlaceDialog() {
        EventDialog.show(itemView.getContext(), mCard.getTripEvent());
    }

}

package sweden.hack.userinfo.viewholders.main;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import sweden.hack.userinfo.AnimationUtils;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.TimeUtils;
import sweden.hack.userinfo.di.DaggerUtils;
import sweden.hack.userinfo.dialogs.EventDialog;
import sweden.hack.userinfo.helpers.CurrencyHelper;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.TripPlaceCard;
import sweden.hack.userinfo.utils.SpannableUtils;

public class TripPlaceViewHolder extends MainViewHolder<TripPlaceCard> {
    private final ImageView mImage;
    private final TextView mTitle;
    private final TextView mDescription;
    private final TextView mTag;
    private final TextView mStartTime;
    private final TextView mEventInfo;
    private final View mDissmissBackground;
    private final View mDissmissYes;
    private final View mDissmissNo;
    private TripPlaceCard mCard;
    private MainCardListener mListener;

    @Inject
    CurrencyHelper mCurrencyHelper;

    public TripPlaceViewHolder(View itemView) {
        super(itemView);
        DaggerUtils.getComponent(itemView.getContext()).inject(this);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mTag = (TextView) itemView.findViewById(R.id.tag);
        mStartTime = (TextView) itemView.findViewById(R.id.start_time);
        mDescription = (TextView) itemView.findViewById(R.id.description);
        mEventInfo = (TextView) itemView.findViewById(R.id.event_info);
        mDissmissBackground = itemView.findViewById(R.id.dismiss_background);
        mDissmissYes = itemView.findViewById(R.id.dismiss_yes);
        mDissmissNo = itemView.findViewById(R.id.dismiss_no);
    }

    @Override
    public void init(TripPlaceCard card, final MainCardListener listener) {
        removeDismissView();
        mCard = card;
        mListener = listener;
        mTitle.setText(card.getTripEvent().getTitle());
        mDescription.setText(card.getTripEvent().getDescription());
        mTag.setText(card.getTripEvent().getTag());
        mStartTime.setText(card.getStartTime());
        setEventInfo();
        Glide.with(itemView.getContext())
                .load(card.getTripEvent().getImage())
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
        titleValueList.add(new SpannableUtils.TitleValue("Price", getPriceString()));
        titleValueList.add(new SpannableUtils.TitleValue("Duration",
                TimeUtils.getTime(mCard.getTripEvent().getDuration())));
        mEventInfo.setText(SpannableUtils.boldTitle(
                titleValueList, SpannableUtils.SeparatorType.SPACE), TextView.BufferType.SPANNABLE);
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
        final EventDialog dialog = new EventDialog(itemView.getContext(), mCard.getTripEvent());
        dialog.show();
    }

}

package sweden.hack.userinfo.viewholders.main;


import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sweden.hack.userinfo.AnimationUtils;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.TimeUtils;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.TripPlaceCard;

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

    public TripPlaceViewHolder(View itemView) {
        super(itemView);
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
        StringBuilder sb = new StringBuilder();
        String priceTitle = "Price: ";
        sb.append(priceTitle);
        sb.append(mCard.getTripEvent().getPrice());
        String durationTitle = "  Duration: ";
        sb.append(durationTitle);
        sb.append(TimeUtils.getTime(mCard.getTripEvent().getDuration()));
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, priceTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        int firstLength = priceTitle.length() + String.valueOf(mCard.getTripEvent().getPrice()).length();
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                firstLength,
                firstLength + durationTitle.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mEventInfo.setText(spannableString, TextView.BufferType.SPANNABLE);
    }
}

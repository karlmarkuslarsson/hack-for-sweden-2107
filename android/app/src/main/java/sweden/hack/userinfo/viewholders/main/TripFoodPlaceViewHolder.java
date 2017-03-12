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

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.TimeUtils;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.TripFoodCard;

public class TripFoodPlaceViewHolder extends MainViewHolder<TripFoodCard> {
    private final ImageView mImage;
    private final TextView mTitle;
    private final TextView mDescription;
    private final TextView mTag;
    private final TextView mStartTime;
    private final TextView mEventInfo;
    private TripFoodCard mCard;

    public TripFoodPlaceViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mDescription = (TextView) itemView.findViewById(R.id.description);
        mTag = (TextView) itemView.findViewById(R.id.tag);
        mStartTime = (TextView) itemView.findViewById(R.id.start_time);
        mEventInfo = (TextView) itemView.findViewById(R.id.event_info);
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

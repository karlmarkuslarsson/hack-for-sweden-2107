package sweden.hack.userinfo.viewholders.main;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.EventCard;

public class EventViewHolder extends MainViewHolder<EventCard> {

    private final ImageView mImage;
    private final TextView mTextTitle;
    private final TextView mTextDescription;
    private final TextView mTextLocation;

    public EventViewHolder(View root) {
        super(root);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTextTitle = (TextView) itemView.findViewById(R.id.title);
        mTextDescription = (TextView) itemView.findViewById(R.id.description);
        mTextLocation = (TextView) itemView.findViewById(R.id.location);
    }

    @Override
    public void init(EventCard card, MainCardListener listener) {
        String title = card.getEvent().getTitle();
        mTextTitle.setText(
                Html.fromHtml(
                        title == null ? "" : title
                )
        );
        String description = card.getEvent().getDescription();
        mTextDescription.setText(Html.fromHtml(
                description == null ? "" : description
        ));

        String location = card.getEvent().getLocation();
        mTextLocation.setText(Html.fromHtml(
                location == null ? "" : location
        ));
        Glide.with(itemView.getContext())
                .load(card.getEvent().getImage())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .fallback(android.R.drawable.ic_input_get)
                .into(mImage);
    }
}

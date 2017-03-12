package sweden.hack.userinfo.viewholders.main;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
        if (description != null && !description.isEmpty()) {
            mTextDescription.setVisibility(View.VISIBLE);
            mTextDescription.setText(Html.fromHtml(
                    description == null ? "" : description
            ));
        } else {
            mTextDescription.setVisibility(View.GONE);
        }

        String location = card.getEvent().getLocation();
        if (location != null && !location.isEmpty()) {
            mTextLocation.setVisibility(View.VISIBLE);
            mTextLocation.setText(Html.fromHtml(
                    location == null ? "" : location
            ));
        } else {
            mTextLocation.setVisibility(View.GONE);
        }

        if (mImage != null) {
            mImage.setVisibility(View.VISIBLE);
            Glide.with(itemView.getContext())
                    .load(card.getEvent().getImage())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .crossFade()
                    .fallback(android.R.drawable.ic_input_get)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            mImage.setVisibility(View.GONE);
                            return true;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(mImage);
        } else {
            mImage.setVisibility(View.GONE);
        }
    }
}

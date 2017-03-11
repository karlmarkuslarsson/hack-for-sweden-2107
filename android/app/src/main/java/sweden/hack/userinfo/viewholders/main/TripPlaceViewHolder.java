package sweden.hack.userinfo.viewholders.main;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.TripPlaceCard;

public class TripPlaceViewHolder extends MainViewHolder<TripPlaceCard> {
    private final ImageView mImage;
    private final TextView mTitle;
    private final TextView mDescription;

    public TripPlaceViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mDescription = (TextView) itemView.findViewById(R.id.description);
    }

    @Override
    public void init(TripPlaceCard card, MainCardListener listener) {
        String imageUrl = null;
        Glide.with(itemView.getContext())
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(mImage);
    }
}

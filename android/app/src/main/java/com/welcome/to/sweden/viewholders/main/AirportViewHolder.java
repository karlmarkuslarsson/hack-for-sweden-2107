package com.welcome.to.sweden.viewholders.main;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.helpers.CurrencyHelper;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.objects.main.AirportCard;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.welcome.to.sweden.utils.ViewUtils.text;

public class AirportViewHolder extends MainViewHolder<AirportCard> {

    @BindView(R.id.content)
    ViewGroup mContent;

    @Inject
    CurrencyHelper mCurrencyHelper;

    public AirportViewHolder(View root) {
        super(root);
        DaggerUtils.getComponent(root.getContext()).inject(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(AirportCard card, MainCardListener listener) {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        List<AirportCard.Alternative> alternatives = card.getAlternatives();
        mContent.removeAllViews();
        for (AirportCard.Alternative alternative : alternatives) {
            View view = inflater.inflate(R.layout.card_airport_part, mContent, false);
            setInfo(alternative, ViewGroup.class.cast(view));
            mContent.addView(view);
        }
    }

    private void setInfo(AirportCard.Alternative line, ViewGroup root) {
        text(root.findViewById(R.id.text1), line.getTitle());
        text(root.findViewById(R.id.text2), line.getTime());
        String cost = mCurrencyHelper.convertToSelectedCurrencyCurrencyString(line.getCost());
        text(root.findViewById(R.id.text3), cost);
        ImageView imageView = (ImageView) root.findViewById(R.id.image);

        Glide.with(itemView.getContext())
                .load(line.getImg())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        resource.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
                        return false;
                    }
                })
                .into(imageView);

    }

}

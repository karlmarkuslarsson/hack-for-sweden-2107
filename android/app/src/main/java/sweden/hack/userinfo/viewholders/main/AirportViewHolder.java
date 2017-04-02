package sweden.hack.userinfo.viewholders.main;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.helpers.CurrencyHelper;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.AirportCard;

public class AirportViewHolder extends MainViewHolder<AirportCard> {

    private final ViewGroup mContent;

    public AirportViewHolder(View root) {
        super(root);
        mContent = (ViewGroup) root.findViewById(R.id.content);
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
        setText(root.findViewById(R.id.text1), line.getTitle());
        setText(root.findViewById(R.id.text2), line.getTime());
        setText(root.findViewById(R.id.text3),
                CurrencyHelper.convertToSelectedCurrencyCurrencyString(line.getCost()));
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

    public void setText(View v, String text) {
        TextView.class.cast(v).setText(text);
    }
}

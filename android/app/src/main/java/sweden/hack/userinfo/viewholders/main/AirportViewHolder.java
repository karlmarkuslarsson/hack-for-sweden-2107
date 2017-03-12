package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.AirportCard;

public class AirportViewHolder extends MainViewHolder<AirportCard> {

    private final ViewGroup mLine1;
    private final ViewGroup mLine2;
    private final ViewGroup mLine3;

    public AirportViewHolder(View root) {
        super(root);
        mLine1 = (ViewGroup) root.findViewById(R.id.line1);
        mLine2 = (ViewGroup) root.findViewById(R.id.line2);
        mLine3 = (ViewGroup) root.findViewById(R.id.line3);
    }

    @Override
    public void init(AirportCard card, MainCardListener listener) {
        setInfo(card.getAlternatives().get(0), mLine1);
        setInfo(card.getAlternatives().get(1), mLine2);
        setInfo(card.getAlternatives().get(2), mLine3);
    }

    private void setInfo(AirportCard.Alternative line, ViewGroup root) {
        setText(root.findViewById(R.id.text1), line.getTitle());
        setText(root.findViewById(R.id.text2), line.getTime());
        setText(root.findViewById(R.id.text3), line.getCost());

        Glide.with(itemView.getContext())
                .load(line.getImg())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into(ImageView.class.cast(root.findViewById(R.id.image)));

    }

    public void setText(View v, String text) {
        TextView.class.cast(v).setText(text);
    }
}

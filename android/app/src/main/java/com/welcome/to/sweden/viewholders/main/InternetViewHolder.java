package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.InternetCard;

public class InternetViewHolder extends MainViewHolder<InternetCard> {

    @BindView(R.id.text_internet)
    TextView mTextInternet;

    public InternetViewHolder(View root) {
        super(root);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(InternetCard card, MainCardListener listener) {
        mTextInternet.setText(card.getInternetInfo());
    }

}

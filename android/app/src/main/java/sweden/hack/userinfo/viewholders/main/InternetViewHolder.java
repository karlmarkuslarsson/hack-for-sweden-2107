package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.InternetCard;

public class InternetViewHolder extends MainViewHolder<InternetCard> {
    private final TextView mTextInternet;

    public InternetViewHolder(View root) {
        super(root);
        mTextInternet = (TextView) itemView.findViewById(R.id.text_internet);
    }

    @Override
    public void init(InternetCard card, MainCardListener listener) {
        mTextInternet.setText(card.getInternetInfo());
    }
}

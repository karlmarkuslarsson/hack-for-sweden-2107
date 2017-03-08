package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.GenderCard;

public class GenderViewHolder extends MainViewHolder<GenderCard> {
    private final TextView mGenderText;

    public GenderViewHolder(View root) {
        super(root);
        mGenderText = (TextView) itemView.findViewById(R.id.text_gender);
    }

    @Override
    public void init(GenderCard card, MainCardListener listener) {
        if (card.getGender() == GenderCard.Gender.WOMAN) {
            mGenderText.setText(itemView.getResources().getString(R.string.gender_woman));
        } else {
            mGenderText.setText(itemView.getResources().getString(R.string.gender_man));
        }
    }

}

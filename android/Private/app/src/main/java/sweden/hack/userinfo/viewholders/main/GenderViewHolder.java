package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.GenderCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class GenderViewHolder extends MainViewHolder {
    private final TextView mGenderText;

    public GenderViewHolder(View root) {
        super(root);
        mGenderText = (TextView) itemView.findViewById(R.id.text_gender);
    }

    @Override
    public void init(MainCard card, MainCardListener listener) {
        GenderCard genderCard = (GenderCard) card;
        if (genderCard.getGender() == GenderCard.Gender.WOMAN) {
            mGenderText.setText(itemView.getResources().getString(R.string.gender_woman));
        } else {
            mGenderText.setText(itemView.getResources().getString(R.string.gender_man));
        }
    }

}

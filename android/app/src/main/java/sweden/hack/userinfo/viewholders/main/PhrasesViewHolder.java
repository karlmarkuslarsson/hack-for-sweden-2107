package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.PhrasesCard;

public class PhrasesViewHolder extends MainViewHolder <PhrasesCard> {
    private final TextView mTextPhrases;

    public PhrasesViewHolder(View root) {
        super(root);
        mTextPhrases = (TextView) root.findViewById(R.id.text_phrases);
    }

    @Override
    public void init(PhrasesCard card, MainCardListener listener) {
        mTextPhrases.setText(card.getPhrases().getPhrases());
    }
}

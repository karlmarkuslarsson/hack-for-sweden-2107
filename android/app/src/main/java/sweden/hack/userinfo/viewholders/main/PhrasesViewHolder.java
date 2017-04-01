package sweden.hack.userinfo.viewholders.main;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.cards.phrases.Phrase;
import sweden.hack.userinfo.objects.main.PhrasesCard;

public class PhrasesViewHolder extends MainViewHolder<PhrasesCard> {
    private final LinearLayout mContent;

    public PhrasesViewHolder(View root) {
        super(root);
        mContent = (LinearLayout) itemView.findViewById(R.id.content);
    }

    @Override
    public void init(PhrasesCard card, MainCardListener listener) {
        mContent.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        for (int i = 0; i < card.getPhrases().getPhrases().size(); i++) {
            LinearLayout linearLayout = new LinearLayout(itemView.getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setWeightSum(2);

            addView(linearLayout, card.getPhrases().getPhrases().get(i), inflater);
            i++;
            if (i < card.getPhrases().getPhrases().size()) {
                addView(linearLayout, card.getPhrases().getPhrases().get(i), inflater);
            }

            mContent.addView(linearLayout);
        }
    }

    private void addView(LinearLayout linearLayout, Phrase phrase, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.row_language, mContent, false);
        TextView swe = (TextView) view.findViewById(R.id.swe);
        TextView eng = (TextView) view.findViewById(R.id.eng);
        swe.setText(phrase.getSwe());
        eng.setText(phrase.getEng());
        linearLayout.addView(view);
    }
}

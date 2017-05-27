package com.welcome.to.sweden.viewholders.main;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.Phrase;
import com.welcome.to.sweden.models.cards.PhrasesCard;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhrasesViewHolder extends MainViewHolder<PhrasesCard> {

    @BindView(R.id.content)
    LinearLayout mContent;

    public PhrasesViewHolder(View root) {
        super(root);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(PhrasesCard card, MainCardListener listener) {
        mContent.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        for (int i = 0; i < card.getPhrases().size(); i++) {
            LinearLayout linearLayout = new LinearLayout(itemView.getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setWeightSum(2);

            addView(linearLayout, card.getPhrases().get(i), inflater);
            i++;
            if (i < card.getPhrases().size()) {
                addView(linearLayout, card.getPhrases().get(i), inflater);
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

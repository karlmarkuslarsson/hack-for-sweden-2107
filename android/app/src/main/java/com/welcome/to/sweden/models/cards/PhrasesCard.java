package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.Phrase;
import com.welcome.to.sweden.models.cards.base.CardComponent;

import java.util.List;

public class PhrasesCard extends CardComponent {

    private List<Phrase> mPhrases;

    public PhrasesCard(List<Phrase> list) {
        mPhrases = list;
    }

    public List<Phrase> getPhrases() {
        return mPhrases;
    }
}

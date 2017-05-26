package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.phrases.Phrases;
import com.welcome.to.sweden.objects.main.base.Card;

public class PhrasesCard extends Card {
    private final Phrases mPhrases;

    public PhrasesCard(Phrases content) {
        if(content != null) {
            mPhrases = content;
        } else {
            mPhrases = new Phrases();
        }
    }

    public Phrases getPhrases() {
        return mPhrases;
    }
}

package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.cards.phrases.Phrases;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class PhrasesCard extends MainCard {
    private final Phrases mPhrases;

    public PhrasesCard(Phrases content) {
        if(content != null) {
            mPhrases = content;
        } else {
            mPhrases = new Phrases();
        }
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_PHRASES;
    }

    public Phrases getPhrases() {
        return mPhrases;
    }
}

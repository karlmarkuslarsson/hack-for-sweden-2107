package com.welcome.to.sweden.listeners;

import com.welcome.to.sweden.models.cards.base.Card;

public interface MainCardListener {
    public void onCardClick(Card card);

    void dismissCard(Card card);
}
